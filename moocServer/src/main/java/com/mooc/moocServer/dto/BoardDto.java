package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.entity.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BoardDto {
    private Long id;
//    private Member writer;
//    private Category category;
    private String writerId;
    private Long categoryId;
    private String content;
    private Long good;
    private List<Comment> commentList = new ArrayList<>();

    public BoardDto(@NonNull String writerId, @NonNull Long categoryId, @NonNull String content){
        this.writerId = writerId;
        this.categoryId = categoryId;
        this.content = content;
        this.good = 0L;
    }

    public static BoardDto entityToDto(Board board){
        return BoardDto.builder()
                .id(board.getId())
                .writerId(board.getWriter().getId())
                .categoryId(board.getCategory().getId())
                .content(board.getContent())
                .good(board.getGood())
                .commentList(board.getComments())
                .build();
    }
}
