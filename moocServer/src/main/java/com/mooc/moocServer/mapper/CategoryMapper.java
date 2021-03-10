package com.mooc.moocServer.mapper;

import com.mooc.moocServer.dto.CategoryDto;
import com.mooc.moocServer.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    // Category -> Dto.Response
    public CategoryDto.Response categoryToCategoryResponse(Category category){
        CategoryDto.Response res = new CategoryDto.Response();

        res.setId(category.getId());
        res.setName(category.getName());
        res.setBoards(category.getBoards());

        return res;
    }

    // List<Category> -> List<Dto.Response>
    public List<CategoryDto.Response> categoryListToCategoryResponseList(List<Category> list){
        List<CategoryDto.Response> ret = new ArrayList<>(list.size());

        for(Category c : list){
            ret.add(categoryToCategoryResponse(c));
        }

        return ret;
    }
}
