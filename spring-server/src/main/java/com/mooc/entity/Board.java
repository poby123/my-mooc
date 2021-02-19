package com.mooc.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Board")
@Getter
@NoArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime registerDate;

	@Column(nullable = false)
	@UpdateTimestamp
	private LocalDateTime updateDate;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;

	@OneToMany(mappedBy = "board", cascade = { CascadeType.ALL })
	private List<Comment> commentList = new ArrayList<>();

	@ManyToOne()
	@JoinColumn(name = "MEMBER_ID", nullable = false)
	private Member writer;

	public Board(String content, Category category, Member writer) {
		this.content = content;
		this.category = category;
		this.writer = writer;
	}
}
