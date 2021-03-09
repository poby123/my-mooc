package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.dto.OrganizationDto;
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
    public ResponseEntity<?> getCategories(@RequestParam("organization") String organizationId) {
        List<CategoryDto.Response> res = categoryService.getCategories(organizationId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long categoryId) {
        CategoryDto.Response res = categoryService.getCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto.AddRequest req) {
        CategoryDto.Response res = categoryService.addCategory(req.getName(), req.getOrganizationId());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
