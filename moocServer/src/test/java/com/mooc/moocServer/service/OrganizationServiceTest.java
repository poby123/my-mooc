package com.mooc.moocServer.service;

import com.mooc.moocServer.domain.Category;
import com.mooc.moocServer.domain.Organization;
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
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void addTest() {
        //given
        String organizationId = "or1";

        //when
        String result = organizationService.addOrganization(organizationId);

        //then
        assertEquals(organizationId, result);
    }

    @Test(expected = IllegalStateException.class)
    public void duplicateTest() {
        //given
        String id = "or1";
        organizationService.addOrganization(id);

        //when
        organizationService.addOrganization(id);

        //then
        fail("duplicate 오류가 발생해야합니다.");
    }

    @Test
    public void getAllOrganizationsTest() {
        //given
        String[] ids = {"or1", "or2", "or3"};
        for (String id : ids) {
            organizationService.addOrganization(id);
        }

        //when
        List<Organization> organizationList = organizationService.getAllOrganizations();

        //then
        for (int i = 0; i < ids.length; i++) {
            Organization o = organizationService.getOrganization(ids[i]);
            assertEquals(o.getId(), organizationList.get(i).getId());
        }
    }

    @Test
    public void getCategories(){
        //given
        String organizationId = "test-organization";

        organizationService.addOrganization(organizationId);
        Organization organization = organizationService.getOrganization(organizationId);

        //when
        categoryService.addCategory("ca1", organizationId);
        categoryService.addCategory("ca2", organizationId);
        categoryService.addCategory("ca3", organizationId);
        categoryService.addCategory("ca4", organizationId);

        //then
        List<Category> categories = organization.getCategories();
        for(Category c : categories){
            log.info(c.getName());
        }
    }
}