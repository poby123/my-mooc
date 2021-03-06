package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Organization;
import com.mooc.moocServer.service.CategoryService;
import com.mooc.moocServer.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    private final OrganizationService organizationService;

    @GetMapping("/category")
    public List<CategoryDto> getCategories(@RequestParam("organization") String organizationId) {
        Organization organization = organizationService.getOrganization(organizationId);
        List<Category> categories = organization.getCategories();
        List<CategoryDto> categoryDtos = new ArrayList<>(categories.size());
        for (Category c : categories) {
            CategoryDto dto = CategoryDto.entityToDto(c);
            categoryDtos.add(dto);
        }
        return categoryDtos;
    }

    @GetMapping("/category/{categoryId}")
    public CategoryDto getCategory(@PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        return CategoryDto.entityToDto(category);
    }

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto dto) {
        Long categoryId = categoryService.addCategory(dto.getName(), dto.getOrganizationId());
//        Long categoryId = categoryService.addCategory(m.get("name"), m.get("organizationId"));
        Category category = categoryService.getCategory(categoryId);

        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryDto.entityToDto(category));
    }

//    @DeleteMapping("/category/{categoryId}")
//    public CategoryDto deleteCategory(@PathVariable Long categoryId){
//
//    }
}
