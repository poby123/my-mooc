package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.dto.OrganizationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestA_addOrganization() throws Exception {
        String content = objectMapper.writeValueAsString(new OrganizationDto.Request("test-organization-id"));
        log.info(content);
        mockMvc.perform(MockMvcRequestBuilders.post("/organization")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void TestB_addMember() throws Exception {
        MemberDto.SignupRequest dto = new MemberDto.SignupRequest("test-member-id", "test-password");
        String member = objectMapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                .content(member)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void TestC_setOrganization() throws Exception {
        // member 확인
        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.put(
                "/member?member=test-member-id&organization=test-organization-id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String mContent = mResult.getResponse().getContentAsString();
        MemberDto.Response res = objectMapper.readValue(mContent, MemberDto.Response.class);
        assertEquals("멤버의 조직을 확인합니다.", "test-organization-id", res.getOrganization().getId());


        // organization 확인
//        MvcResult orResult = mockMvc.perform(MockMvcRequestBuilders.get("/organization" + "/test-organization")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//        String orContent = orResult.getResponse().getContentAsString();
//        OrganizationDto.Info orDto = objectMapper.readValue(orContent, OrganizationDto.Info.class);
//        assertEquals("조직의 멤버를 확인합니다.", "test-member-id", orDto.getMembers().get(0).getId());
    }

    //
    @Test
    public void TestD_getMemberList() throws Exception {
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
        for(MemberDto.SimpleResponse res : list){
            assertEquals(res.getId(), "test-member-id");
        }
    }

    @Test
    public void TestE_getMember() throws Exception {
        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/member/test-member-id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String strResult = mResult.getResponse().getContentAsString();
        MemberDto.Response res = objectMapper.readValue(strResult, MemberDto.Response.class);
        assertEquals(res.getId(), "test-member-id");
    }
}