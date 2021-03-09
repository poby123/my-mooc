package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.mapper.CategoryMapper;
import com.mooc.moocServer.repository.CategoryRepository;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.NonNull;
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
    private CategoryMapper categoryMapper = new CategoryMapper();

    @Transactional
    public CategoryDto.Response addCategory(String name, String organizationId) {
        Organization organization = organizationRepository.findOne(organizationId);
        Category category = Category.createCategory(name, organization);

        organization.addCategory(category);
        categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponse(category);
    }

    public CategoryDto.Response getCategory(Long id) {
        Category category = categoryRepository.findOne(id);
        return categoryMapper.categoryToCategoryResponse(category);
    }

    public List<CategoryDto.Response> getCategories(@NonNull String organizationId) {
        Organization organization = organizationRepository.findOne(organizationId);
        return categoryMapper.categoryListToCategoryResponseList(organization.getCategories());
    }
}
