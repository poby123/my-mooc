package com.mooc.moocServer.mapper;

import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final BoardMapper boardMapper;

    // Category -> Dto.Response
    public CategoryDto.Response categoryToCategoryResponse(Category category){
        CategoryDto.Response res = new CategoryDto.Response();

        res.setId(category.getId());
        res.setName(category.getName());
        res.setBoards(boardMapper.boardToBoardResponseList(category.getBoards()));

        return res;
    }

    // Category -> Dto.SimpleResponse
    public CategoryDto.SimpleResponse categoryToCategorySimpleResponse(Category category){
        return new CategoryDto.SimpleResponse(category.getId(), category.getName());
    }

    // List<Category> -> List<Dto.Response>
    public List<CategoryDto.Response> categoryListToCategoryResponseList(List<Category> list){
        List<CategoryDto.Response> ret = new ArrayList<>(list.size());

        for(Category c : list){
            ret.add(categoryToCategoryResponse(c));
        }

        return ret;
    }

    // List<Category> -> List<Dto.SimpleResponse>
    public List<CategoryDto.SimpleResponse> categoryListToCategorySimpleResponseList(List<Category> list){
        List<CategoryDto.SimpleResponse> ret = new ArrayList<>(list.size());

        for(Category c : list){
            ret.add(categoryToCategorySimpleResponse(c));
        }

        return ret;
    }
}
