package com.mooc.moocServer.repository;

import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Organization;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
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
@Transactional
@Slf4j
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Test
    public void saveOrganization() {
        organizationRepository.save(new Organization("Test Organization"));
        log.info("test message");
    }

    @Test
    @Ignore
    public void saveTest() {
        Organization organization = organizationRepository.findOne("Test Organization");
        Category category = Category.createCategory("test-category", organization);
        categoryRepository.save(category);

        Category one = categoryRepository.findOne(1L);

        assertEquals("category save test", category, one);

        organization.addCategory(category);

        List<Category> categories = organization.getCategories();
        System.out.println("The size is : " + categories.size());
        categories.forEach(c -> System.out.println("The id is : " + c.getId()));
    }
}