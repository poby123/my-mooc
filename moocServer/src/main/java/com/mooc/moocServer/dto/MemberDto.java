package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.entity.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberDto {
    private String id;
    private String image;
    private MemberRole role;
    private List<Board> boards;
    private List<Comment> comments;
}
