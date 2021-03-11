package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.BoardDto;
import com.mooc.moocServer.dto.CategoryDto;
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
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws Exception {
        ControllerTestUtility.addOrganization(mockMvc, objectMapper);
        ControllerTestUtility.addMember(mockMvc, objectMapper);
        ControllerTestUtility.setMemberOrganization(mockMvc, objectMapper, "test-member-id", "test-organization-id");
        ControllerTestUtility.addCategory(mockMvc, objectMapper);
    }

    @Test
    public void addBoard() throws Exception {
        // 글 확인
        BoardDto.Response res = ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content");
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.getWriter().getId());
        assertEquals("글 내용을 확인합니다.", "test-board content", res.getContent());

        // 카테고리에서 글 확인
        CategoryDto.Response category = ControllerTestUtility.getCategory(mockMvc, objectMapper);
        List<BoardDto.Response> boards = category.getBoards();
        assertEquals("글 갯수를 확인합니다.", 1, boards.size());
    }

    @Test
    public void getBoard() throws Exception {
        BoardDto.Response response = ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content");
        Long originalId = response.getId();

        BoardDto.Response res = ControllerTestUtility.getBoard(mockMvc, objectMapper, String.valueOf(originalId));
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.getWriter().getId());
        assertEquals("글 내용을 확인합니다.", "test-board content", res.getContent());
        assertEquals("글 번호를 확인합니다.", originalId, res.getId());
    }

    @Test
    public void getBoards() throws Exception {
        ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content1");
        ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content2");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board?category=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        List<BoardDto.Response> res = objectMapper.readValue(resultString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, BoardDto.Response.class));
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.get(0).getWriter().getId());
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.get(1).getWriter().getId());

        assertEquals("글 내용을 확인합니다.", "test-board content1", res.get(0).getContent());
        assertEquals("글 내용을 확인합니다.", "test-board content2", res.get(1).getContent());
    }

}