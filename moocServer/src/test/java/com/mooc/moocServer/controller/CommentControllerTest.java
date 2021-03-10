package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.*;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws Exception {
        TestA_addOrganization();
        TestB_addMember();
        TestC_setOrganization();
        TestD_addCategory();
        TestE_addBoard();
    }

    @Test
    public void addComment() throws Exception {
        String content = objectMapper.writeValueAsString(new CommentDto.AddRequest("test-member-id", 2L, "comment-content"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/comment")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        CommentDto.Response res = objectMapper.readValue(resultString, CommentDto.Response.class);
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.getWriter().getId());
        assertEquals("글 내용을 확인합니다.", "comment-content", res.getContent());
    }

    @Test
    public void getComment() throws Exception {
        addComment();
        addComment();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/comment?board=2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        List<CommentDto.Response> res = objectMapper.readValue(
                resultString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, CommentDto.Response.class)
        );
        for (CommentDto.Response cd : res) {
            assertEquals("댓글의 보드 아이디를 확인합니다.","2", String.valueOf(cd.getBoardId()));
        }
    }


    /*
     * Add
     * */
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

    public void TestD_addCategory() throws Exception {
        String content = objectMapper.writeValueAsString(new CategoryDto.AddRequest("test-category-name", "test-organization-id"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/category")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        CategoryDto.Response res = objectMapper.readValue(resultString, CategoryDto.Response.class);
        log.info("카테고리 아이디 : " + res.getId());
        assertEquals("카테고리 이름을 확인합니다.", "test-category-name", res.getName());
    }


    public void TestE_addBoard() throws Exception {

        String content = objectMapper.writeValueAsString(new BoardDto.AddRequest("test-member-id", 1L, "test-board content"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        BoardDto.Response res = objectMapper.readValue(resultString, BoardDto.Response.class);
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.getWriter().getId());
        assertEquals("글 내용을 확인합니다.", "test-board content", res.getContent());
    }
}