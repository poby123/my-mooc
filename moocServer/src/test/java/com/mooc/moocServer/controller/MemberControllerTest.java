package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.dto.OrganizationDto;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Before
    public void addOrganization() throws Exception {
        String content = objectMapper.writeValueAsString(new OrganizationDto("test-organization"));

        mockMvc.perform(MockMvcRequestBuilders.post("/organization")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Before
    public void addMember() throws Exception {
        MemberDto dto = MemberDto.builder().id("test-member-id").password("test-password").build();
        String member = objectMapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                .content(member)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void setOrganization() throws Exception {
        // member 확인
        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.put(
                "/member?member=test-member-id&organization=test-organization")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String mContent = mResult.getResponse().getContentAsString();
        MemberDto mDto = objectMapper.readValue(mContent, MemberDto.class);
        assertEquals("멤버의 조직을 확인합니다.", "test-organization", mDto.getOrganizationId());

        // organization 확인
        MvcResult orResult = mockMvc.perform(MockMvcRequestBuilders.get("/organization" + "/test-organization")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String orContent = orResult.getResponse().getContentAsString();
        OrganizationDto orDto = objectMapper.readValue(orContent, OrganizationDto.class);
        assertEquals("조직의 멤버를 확인합니다.", "test-member-id", orDto.getMembers().get(0).getId());
    }

    @Test
    public void getMemberList() throws Exception {
        setOrganization();

        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/member?organization=test-organization")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getMember() throws Exception {
        setOrganization();

        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/member/test-member-id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}