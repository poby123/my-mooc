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

    public void setup() throws Exception {
        ControllerTestUtility.addOrganization(mockMvc, objectMapper);
        ControllerTestUtility.addMember(mockMvc, objectMapper);
        ControllerTestUtility.setMemberOrganization(mockMvc, objectMapper, "test-member-id", "test-organization-id");
        ControllerTestUtility.addCategory(mockMvc, objectMapper);
        ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content");
    }

    @Test
    public void addComment() throws Exception {
        setup();
        CommentDto.Response res = ControllerTestUtility.addComment(mockMvc, objectMapper, "test-member-id", 2L, "comment-content");

        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.getWriter().getId());
        assertEquals("글 내용을 확인합니다.", "comment-content", res.getContent());
    }

    @Test
    public void getComment() throws Exception {
        setup();
        // 댓글 추가
        ControllerTestUtility.addComment(mockMvc, objectMapper, "test-member-id", 2L, "comment-content");
        ControllerTestUtility.addComment(mockMvc, objectMapper, "test-member-id", 2L, "comment-content");

        // 글 댓글들 가져오기
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
            assertEquals("글쓴이를 확인합니다.", "test-member-id", cd.getWriter().getId());
            assertEquals("글 내용을 확인합니다.", "comment-content", cd.getContent());
            assertEquals("댓글의 보드 아이디를 확인합니다.","2", String.valueOf(cd.getBoardId()));
        }
    }

    @Test
    public void 댓글추가시존재하지않는멤버혹은글테스트() throws Exception{
        String content = objectMapper.writeValueAsString(new CommentDto.AddRequest("test-member-id", 2L, "comment-content"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/comment")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void 댓글목록조회시존재하지않는게시물테스트() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/comment?board=2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}