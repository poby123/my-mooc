package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.CategoryDto;
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

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void addOrganization() throws Exception {
        String content = objectMapper.writeValueAsString(new OrganizationDto("test-organization-id"));

        mockMvc.perform(MockMvcRequestBuilders.post("/organization")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
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
    public void getCategory() throws Exception {
        addCategory();

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

    @Test
    public void getCategories() throws Exception {
        addCategory();
        addCategory();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/category?organization=test-organization-id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        List<CategoryDto> categoryDto = objectMapper.readValue(resultString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, CategoryDto.class));
        for (CategoryDto dto : categoryDto) {
            assertEquals("카테고리 이름을 확인합니다.", "test-category-name", dto.getName());
            assertEquals("카테고리 조직을 확인합니다.", "test-organization-id", dto.getOrganizationId());
        }
    }
}