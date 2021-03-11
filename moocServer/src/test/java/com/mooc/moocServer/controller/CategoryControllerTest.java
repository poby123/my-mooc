package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.CategoryDto;
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
    public void setup() throws Exception {
        ControllerTestUtility.addOrganization(mockMvc, objectMapper);
    }

    @Test
    public void addCategory() throws Exception {
        // 카테고리 추가. 확인
        CategoryDto.Response res = ControllerTestUtility.addCategory(mockMvc, objectMapper);
        log.info("카테고리 아이디 : " + res.getId());
        assertEquals("카테고리 이름을 확인합니다.", "test-category-name", res.getName());

        // 조직에서 카테고리 확인
        OrganizationDto.Response organization = ControllerTestUtility.getOrganization(mockMvc, objectMapper);
        assertEquals("조직에서 카테고리를 확인합니다.", "test-category-name", organization.getCategories().get(0).getName());
    }

    @Test
    public void getCategory() throws Exception {
        // 카테고리 추가
        CategoryDto.Response addRes = ControllerTestUtility.addCategory(mockMvc, objectMapper);
        Long id = addRes.getId();

        // 확인
        CategoryDto.Response res = ControllerTestUtility.getCategory(mockMvc, objectMapper, String.valueOf(id));
        assertEquals("카테고리 이름을 확인합니다.", "test-category-name", res.getName());
    }

    @Test
    public void getCategories() throws Exception {
        ControllerTestUtility.addCategory(mockMvc, objectMapper, "category-name-1");
        ControllerTestUtility.addCategory(mockMvc, objectMapper, "category-name-2");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/category?organization=test-organization-id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        List<CategoryDto.Response> res = objectMapper.readValue(resultString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, CategoryDto.Response.class));

        assertEquals("카테고리 이름을 확인합니다.", "category-name-1", res.get(0).getName());
        assertEquals("카테고리 이름을 확인합니다.", "category-name-2", res.get(1).getName());
    }
}