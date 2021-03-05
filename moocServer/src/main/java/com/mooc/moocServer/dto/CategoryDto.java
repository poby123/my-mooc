package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private List<Board> boards;
    private Organization organization;
}
