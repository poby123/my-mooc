package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardDto {
    private Long id;
    private Member writer;
    private Category category;
    private String content;
    private Long good;
    private List<Comment> commentList;
}
