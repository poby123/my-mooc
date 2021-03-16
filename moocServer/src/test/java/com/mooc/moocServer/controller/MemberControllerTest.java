package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.dto.OrganizationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String organizationId;

    public void setup() throws Exception {
        OrganizationDto.Response response = ControllerTestUtility.addOrganization(mockMvc, objectMapper);
        organizationId = response.getId();
    }

    @Test
    public void addMember() throws Exception {
        MemberDto.Response response = ControllerTestUtility.addMember(mockMvc, objectMapper);
        assertEquals("추가한 멤버의 아이디를 확인합니다.", "test-member-id", response.getId());
    }

    @Test
    public void 로그인테스트() throws Exception{
        addMember();
        MemberDto.SignInResponse res1 = ControllerTestUtility.signin(mockMvc, objectMapper, "test-member-id", "test-password", MockMvcResultMatchers.status().isOk());
        log.info(res1.getResult());
        MemberDto.SignInResponse res2 = ControllerTestUtility.signin(mockMvc, objectMapper, "test-member-id", "test&password", MockMvcResultMatchers.status().isBadRequest());
        log.info(res2.getResult());
    }

    @Test
    public void 정상적인토큰권한확인() throws Exception{
        addMember();
        MemberDto.SignInResponse res1 = ControllerTestUtility.signin(mockMvc, objectMapper, "test-member-id", "test-password", MockMvcResultMatchers.status().isOk());
        String jwtToken = res1.getResult();

        String content = objectMapper.writeValueAsString(new OrganizationDto.Request("test-organization-id"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/organization")
                .header("X-AUTH-TOKEN", jwtToken)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        OrganizationDto.Response response = objectMapper.readValue(resultString, OrganizationDto.Response.class);
        assertEquals("test-organization-id", response.getId());
    }

    @Test
    public void 비정상적인토큰권한확인() throws Exception{
        String content = objectMapper.writeValueAsString(new OrganizationDto.Request("test-organization-id"));

        mockMvc.perform(MockMvcRequestBuilders.post("/organization")
                .header("X-AUTH-TOKEN", "thisiswrongjwttoken!!")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void setOrganization() throws Exception {
        setup();
        addMember();

        // member 수정 및 확인
        MemberDto.Response res = ControllerTestUtility.setMemberOrganization(mockMvc, objectMapper, "test-member-id", "test-organization-id");
        assertEquals("멤버의 조직을 확인합니다.", "test-organization-id", res.getOrganizationId());

        // organization 확인
        OrganizationDto.Response response = ControllerTestUtility.getOrganization(mockMvc, objectMapper);
        assertEquals("조직의 첫번째 멤버의 아이디를 확인합니다.", "test-member-id", response.getMembers().get(0).getId());
    }

    //
    @Test
    public void getMemberList() throws Exception {
        setOrganization();

        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/member?organization=test-organization-id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String mString = mResult.getResponse().getContentAsString();
        List<MemberDto.SimpleResponse> list = objectMapper.readValue(
                mString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, MemberDto.SimpleResponse.class)
        );
        for (MemberDto.SimpleResponse res : list) {
            assertEquals(res.getId(), "test-member-id");
        }
    }

    @Test
    public void getMember() throws Exception {
        addMember();
        MemberDto.Response res = ControllerTestUtility.getMember(mockMvc, objectMapper);
        assertEquals(res.getId(), "test-member-id");
    }

    @Test
    public void 멤버추가시널값테스트() throws Exception {
        MemberDto.SignupRequest dto = new MemberDto.SignupRequest(null, null);
        String member = objectMapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member")
                .content(member)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void 멤버수정시존재하지않는조직으로테스트() throws Exception {
        addMember();

        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.put(
                "/member?member=test-member-id&organization=" + organizationId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void 조회시존재하지않는멤버() throws Exception {
        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/member/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void 전체멤버조회시시존재하지않은조직인경우() throws Exception {
        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/member?organization=anything")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}