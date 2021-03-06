package com.mooc.moocServer.dto;

import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Comment;
import com.mooc.moocServer.entity.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CommentDto {
    private Long id;
    private String writerId;
    private Long boardId;
    private String content;

    public CommentDto(@NonNull String writerId, @NonNull Long boardId, @NonNull String content) {
        this.writerId = writerId;
        this.boardId = boardId;
        this.content = content;
    }

    public static CommentDto entityToDto(Comment c) {
        return CommentDto.builder()
                .id(c.getId())
                .writerId(c.getWriter().getId())
                .boardId(c.getBoard().getId())
                .content(c.getContent())
                .build();
    }
}
