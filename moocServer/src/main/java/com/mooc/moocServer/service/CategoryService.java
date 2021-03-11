package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.mapper.CategoryMapper;
import com.mooc.moocServer.repository.CategoryRepository;
import com.mooc.moocServer.repository.OrganizationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final OrganizationRepository organizationRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryDto.Response addCategory(String name, String organizationId) throws NullPointerException {
        Organization organization = organizationRepository.findOne(organizationId);

        if (organization == null) {
            throw new NullPointerException("존재하지 않는 조직에 카테고리를 추가하려고 했습니다.");
        }

        Category category = Category.createCategory(name, organization);

        organization.addCategory(category);
        categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponse(category);
    }

    public CategoryDto.Response getCategory(@NonNull Long id) throws NullPointerException {
        Category category = categoryRepository.findOne(id);

        if (category == null) {
            throw new NullPointerException("찾는 카테고리가 존재하지 않습니다.");
        }

        return categoryMapper.categoryToCategoryResponse(category);
    }

    public List<CategoryDto.Response> getCategories(@NonNull String organizationId) throws NullPointerException{
        Organization organization = organizationRepository.findOne(organizationId);

        if(organization == null){
            throw new NullPointerException("찾는 카테고리가 속해있는 조직이 존재하지 않습니다.");
        }

        return categoryMapper.categoryListToCategoryResponseList(organization.getCategories());
    }
}
