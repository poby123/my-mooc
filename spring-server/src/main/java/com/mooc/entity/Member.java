package com.mooc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Member")
@Getter
@NoArgsConstructor
public class Member {
	@Id
	@Column(name = "MEMBER_ID")
	private String id;

	@Column(nullable = false, name = "MEMBER_NAME")
	private String name;

	@Column(nullable = false, name = "MEMBER_PASSWORD")
	private String password;

	@ManyToOne()
	@JoinColumn(name = "ORGANIZATION_ID")
	private Organization organization;

	@OneToMany(mappedBy = "writer")
	List<Board> boardList = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	List<Participant> participationList = new ArrayList<>();

	public Member(String id, String name, String password, Organization organization) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.organization = organization;
	}
}
