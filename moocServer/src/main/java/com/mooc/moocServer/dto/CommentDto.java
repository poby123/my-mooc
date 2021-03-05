package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long id;
    private Member writer;
    private Board board;
    private String content;
}
