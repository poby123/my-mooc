package com.mooc.moocServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.dto.OrganizationDto;
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

//    public void setup() throws Exception {
//        ControllerTestUtility.addOrganization(mockMvc, objectMapper);
//    }

    @Test
    public void addCategory() throws Exception {
        // 조직 추가
        ControllerTestUtility.addOrganization(mockMvc, objectMapper);

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
        // 조직 추가
        ControllerTestUtility.addOrganization(mockMvc, objectMapper);

        // 카테고리 추가
        CategoryDto.Response addRes = ControllerTestUtility.addCategory(mockMvc, objectMapper);
        Long id = addRes.getId();

        // 확인
        CategoryDto.Response res = ControllerTestUtility.getCategory(mockMvc, objectMapper, String.valueOf(id));
        assertEquals("카테고리 이름을 확인합니다.", "test-category-name", res.getName());
    }

    @Test
    public void getCategories() throws Exception {
        // 조직 추가
        ControllerTestUtility.addOrganization(mockMvc, objectMapper);

        // 카테고리 추가
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

    @Test
    public void 추가하는데조직이없는경우() throws Exception {
        String name = "test-category-name";
        String content = objectMapper.writeValueAsString(new CategoryDto.AddRequest(name, "test-organization-id"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/category")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void 찾는카테고리가없는경우() throws Exception{
        // 카테고리 조회
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/category/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void 전체카테고리를조회할때조직이없는경우() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/category?organization=test-organization-id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) // 뭘로 설계할지 결정...
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}