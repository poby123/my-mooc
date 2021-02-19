package com.mooc.entity;

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

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Category")
@Getter
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Long id;

	@Column(name = "CATEGORY_TITLE", nullable = false)
	private String title;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<>();

	@OneToMany(mappedBy = "category")
	private List<Participant> participantList = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;

	public Category(String title, Organization organization) {
		this.title = title;
		this.organization = organization;
	}
}
