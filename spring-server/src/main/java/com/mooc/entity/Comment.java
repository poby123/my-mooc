package com.mooc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Comment")
@Getter
@NoArgsConstructor
public class Comment {
	@Id
	@Column(name = "COMMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "COMMENT_CONTENT", nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "BOARD_ID", nullable = false)
	private Board board;

	@ManyToOne()
	@JoinColumn(name = "MEMBER_ID", nullable = false)
	private Member writer;

	public Comment(String content, Board board, Member writer) {
		this.content = content;
		this.board = board;
		this.writer = writer;
	}
}
