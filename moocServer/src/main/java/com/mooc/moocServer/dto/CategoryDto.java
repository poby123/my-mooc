package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Organization;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private List<Board> boards = new ArrayList<>();
    private String organizationId;

    public CategoryDto(String name, String organizationId){
        this.name = name;
        this.organizationId = organizationId;
    }

    public static CategoryDto entityToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .boards(category.getBoards())
                .organizationId(category.getOrganization().getId())
                .build();
    }

}
