package com.mooc.moocServer.service;

import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.repository.CategoryRepository;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//    public List<Category> getCategories(Organization organization) {
//        return categoryRepository.findByOrganization(organization);
//    }

    public Category getCategory(Long id) {
        return categoryRepository.findOne(id);
    }

}
