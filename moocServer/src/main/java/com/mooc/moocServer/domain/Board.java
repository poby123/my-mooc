package com.mooc.moocServer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "board_content")
    private String content;

    @Column(name = "board_good")
    private Long good;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    // == 생성 메서드 == //
    public static Board createBoard(Member member, Category category, String content) {
        Board board = new Board();
        board.setWriter(member);
        board.setCategory(category);
        board.setContent(content);
        board.setGood(0L);
        board.setComments(new ArrayList<>());

        return board;
    }
}
