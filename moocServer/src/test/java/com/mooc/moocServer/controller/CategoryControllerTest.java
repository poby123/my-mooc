package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.dto.OrganizationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void TestB_addCategory() throws Exception {
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

    @Test
    public void TestC_getCategory() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/category/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        CategoryDto.Response res = objectMapper.readValue(resultString, CategoryDto.Response.class);
        assertEquals("카테고리 이름을 확인합니다.", "test-category-name", res.getName());
    }

    @Test
    public void TestD_getCategories() throws Exception {
        TestA_addOrganization();
        TestB_addCategory();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/category?organization=test-organization-id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        List<CategoryDto.Response> res = objectMapper.readValue(resultString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, CategoryDto.Response.class));
        for (CategoryDto.Response dto : res) {
            assertEquals("카테고리 이름을 확인합니다.", "test-category-name", dto.getName());
        }
    }
}