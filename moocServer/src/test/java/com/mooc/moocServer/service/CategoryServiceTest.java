package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Organization;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Transactional
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrganizationService organizationService;

    @Test(expected = NullPointerException.class)
    public void 조직이없는상태에서추가() {
        CategoryDto.Response res = categoryService.addCategory("test-category-name", "notExist");
        log.info(res.toString());
    }
}