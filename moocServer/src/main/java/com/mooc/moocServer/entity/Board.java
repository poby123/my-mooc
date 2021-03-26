package com.mooc.moocServer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonBackReference("member-board")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference("category-board")
    private Category category;

    @Column(name = "board_content")
    private String content;

    @Column(name = "board_good")
    private Long good;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonManagedReference("board-comment")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonManagedReference("board-file")
    private List<UploadFile> files = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "board_reg_date")
    Date regDate;

    @UpdateTimestamp
    @Column(name = "board_edit_date")
    Date editDate;

    // == 생성 메서드 == //
    public static Board createBoard(Member member, Category category, String content) {
        Board board = new Board();
        board.writer = member;
        board.category = category;
        board.content = content;
        board.good = 0L;
        board.comments = new ArrayList<>();

        return board;
    }

    // == 연관관계 메서드 == //
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addFile(UploadFile file) {
        this.files.add(file);
    }

    public void setFiles(List<UploadFile> uploadedFiles) {
        this.files = uploadedFiles;
    }
}
