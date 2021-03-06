package com.mooc.moocServer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonBackReference("member-comment")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonBackReference("board-comment")
    private Board board;

    @Column(name = "comment_content")
    private String content;

    @CreationTimestamp
    @Column(name = "comment_reg_date")
    Date regDate;

    @UpdateTimestamp
    @Column(name = "comment_edit_date")
    Date editDate;

    // == 생성 메서드 == //
    public static Comment createComment(Member writer, Board board, String content){
        Comment comment = new Comment();
        comment.writer = writer;
        comment.board = board;
        comment.content = content;

        return comment;
    }
}
