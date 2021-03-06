package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.BoardDto;
import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.dto.OrganizationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws Exception {
        addOrganization();
        addMember();
        addCategory();
        setOrganization();
    }

    @Test
    public void addBoard() throws Exception {
        String content = objectMapper.writeValueAsString(new BoardDto("test-member-id", 1L, "test-board content"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        BoardDto boardDto = objectMapper.readValue(resultString, BoardDto.class);
        assertEquals("글쓴이를 확인합니다.", "test-member-id", boardDto.getWriterId());
        assertEquals("카테고리 아이디를 확인합니다.", "1", String.valueOf(boardDto.getCategoryId()));
        assertEquals("글 내용을 확인합니다.", "test-board content", boardDto.getContent());

        getCategory();
    }

    @Test
    public void getBoard() {
    }

    @Test
    public void getBoards() {
    }

    public void getCategory() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/category/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        CategoryDto categoryDto = objectMapper.readValue(resultString, CategoryDto.class);
        assertEquals("카테고리 이름을 확인합니다.", "test-category-name", categoryDto.getName());
        assertEquals("카테고리 조직을 확인합니다.", "test-organization-id", categoryDto.getOrganizationId());
    }

    /*
     * Add
     * */
    @Test
    public void addOrganization() throws Exception {
        String content = objectMapper.writeValueAsString(new OrganizationDto("test-organization-id"));

        mockMvc.perform(MockMvcRequestBuilders.post("/organization")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    public void addCategory() throws Exception {
        String content = objectMapper.writeValueAsString(new CategoryDto("test-category-name", "test-organization-id"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/category")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        CategoryDto categoryDto = objectMapper.readValue(resultString, CategoryDto.class);
        assertEquals("카테고리 이름을 확인합니다.", "test-category-name", categoryDto.getName());
        assertEquals("카테고리 조직을 확인합니다.", "test-organization-id", categoryDto.getOrganizationId());
    }

    @Test
    public void addMember() throws Exception {
        MemberDto dto = MemberDto.builder().id("test-member-id").password("test-password").build();
        String member = objectMapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                .content(member)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    public void setOrganization() throws Exception {
        // member 확인
        MvcResult mResult = mockMvc.perform(MockMvcRequestBuilders.put(
                "/member?member=test-member-id&organization=test-organization-id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String mContent = mResult.getResponse().getContentAsString();
        MemberDto mDto = objectMapper.readValue(mContent, MemberDto.class);
        assertEquals("멤버의 조직을 확인합니다.", "test-organization-id", mDto.getOrganizationId());

        // organization 확인
        MvcResult orResult = mockMvc.perform(MockMvcRequestBuilders.get("/organization" + "/test-organization-id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String orContent = orResult.getResponse().getContentAsString();
        OrganizationDto orDto = objectMapper.readValue(orContent, OrganizationDto.class);
        assertEquals("조직의 멤버를 확인합니다.", "test-member-id", orDto.getMembers().get(0).getId());
    }
}