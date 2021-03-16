package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.BoardDto;
import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.dto.MemberDto;
import com.mooc.moocServer.entity.Board;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public void setup() throws Exception {
        ControllerTestUtility.addOrganization(mockMvc, objectMapper);
        ControllerTestUtility.addMember(mockMvc, objectMapper);
        ControllerTestUtility.setMemberOrganization(mockMvc, objectMapper, "test-member-id", "test-organization-id");
        ControllerTestUtility.addCategory(mockMvc, objectMapper);
    }

    @Test
    public void addBoard() throws Exception {
        setup();

        // 글 확인
        BoardDto.Response res = ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content");
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.getWriter().getId());
        assertEquals("글 내용을 확인합니다.", "test-board content", res.getContent());

        // 카테고리에서 글 확인
        CategoryDto.Response category = ControllerTestUtility.getCategory(mockMvc, objectMapper);
        List<BoardDto.Response> boards1 = category.getBoards();
        assertEquals("글 갯수를 확인합니다.", 1, boards1.size());
        
        // 멤버에서 글 확인
        MemberDto.Response member = ControllerTestUtility.getMember(mockMvc, objectMapper);
        List<Board> boards2 = member.getBoards();
        assertEquals("글 갯수를 확인합니다.", 1, boards2.size());
    }

    @Test
    public void getBoard() throws Exception {
        setup();
        BoardDto.Response response = ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content");
        Long originalId = response.getId();

        BoardDto.Response res = ControllerTestUtility.getBoard(mockMvc, objectMapper, String.valueOf(originalId));
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.getWriter().getId());
        assertEquals("글 내용을 확인합니다.", "test-board content", res.getContent());
        assertEquals("글 번호를 확인합니다.", originalId, res.getId());
    }

    @Test
    public void getBoards() throws Exception {
        setup();
        ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content1");
        ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content2");
        ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content3");
        ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content4");
        ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content5");
        ControllerTestUtility.addBoard(mockMvc, objectMapper, "test-member-id", 1L, "test-board content6");

        // 0 페이지
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board?category=1&page=0&size=2&sort=id,DESC")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        List<BoardDto.Response> res = objectMapper.readValue(resultString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, BoardDto.Response.class));
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.get(0).getWriter().getId());
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.get(1).getWriter().getId());

        assertEquals("글 내용을 확인합니다.", "test-board content6", res.get(0).getContent());
        assertEquals("글 내용을 확인합니다.", "test-board content5", res.get(1).getContent());

        // 1 페이지
        result = mockMvc.perform(MockMvcRequestBuilders.get("/board?category=1&page=1&size=2&sort=id,DESC")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        resultString = result.getResponse().getContentAsString();
        res = objectMapper.readValue(resultString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, BoardDto.Response.class));
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.get(0).getWriter().getId());
        assertEquals("글쓴이를 확인합니다.", "test-member-id", res.get(1).getWriter().getId());

        assertEquals("글 내용을 확인합니다.", "test-board content4", res.get(0).getContent());
        assertEquals("글 내용을 확인합니다.", "test-board content3", res.get(1).getContent());
    }

    @Test
    public void 글추가시널값테스트() throws Exception {
        String reqString = objectMapper.writeValueAsString(new BoardDto.AddRequest(null, null, null));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board")
                .content(reqString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void 글조회시존재하지않은카테고리테스트() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board?category=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void 글조회시존재하지않는글테스트() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}