package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.dto.OrganizationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.*;

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

    @Before
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
    public void setOrganization() throws Exception {
        addMember();

        // member 수정 및 확인
        MemberDto.Response res = ControllerTestUtility.setMemberOrganization(mockMvc, objectMapper, "test-member-id","test-organization-id");
        assertEquals("멤버의 조직을 확인합니다.", "test-organization-id", res.getOrganization().getId());

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
}