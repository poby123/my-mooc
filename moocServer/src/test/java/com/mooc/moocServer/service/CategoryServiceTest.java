//package com.mooc.moocServer.service;
//
//import com.mooc.moocServer.entity.Category;
//import com.mooc.moocServer.entity.Organization;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//@Transactional
//public class CategoryServiceTest {
//
//    @Autowired
//    CategoryService categoryService;
//
//    @Autowired
//    OrganizationService organizationService;
//
//    @Test
//    public void addCategory() {
//        //given
//        organizationService.addOrganization("or1");
//        Long categoryId = categoryService.addCategory("cn1", "or1");
//
//        //when
//        Category category = categoryService.getCategory(categoryId);
//
//        //then
//        assertEquals(category.getId(), categoryId);
//        Organization organization = organizationService.getOrganization("or1");
//        log.info(organization.getId());
//
//        List<Category> categoryList = organization.getCategories();
//        log.info("category list size is : " + categoryList.size());
//        for(Category c : categoryList){
//            log.info(c.getName());
//        }
//    }
//}