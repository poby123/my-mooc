package com.mooc.moocServer.service;

import com.mooc.moocServer.domain.Board;
import com.mooc.moocServer.domain.Category;
import com.mooc.moocServer.domain.Organization;
import com.mooc.moocServer.repository.CategoryRepository;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final OrganizationRepository organizationRepository;

    @Transactional
    public Long addCategory(String name, String organizationId) {
        Organization organization = organizationRepository.findOne(organizationId);
        Category category = Category.createCategory(name, organization);
        organization.addCategory(category);
        categoryRepository.save(category);
        return category.getId();
    }

//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//
//    public List<Category> getCategories(Organization organization) {
//        return categoryRepository.findByOrganization(organization);
//    }

    public Category getCategory(Long id) {
        return categoryRepository.findOne(id);
    }

    public List<Board> getBoards(Long categoryId){
        Category category = categoryRepository.findOne(categoryId);
        return category.getBoards();
    }

}
