package com.mooc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Organization")
@Getter
@NoArgsConstructor
public class Organization {
	@Id
	@Column(name = "ORGANIZATION_ID")
	private String id;

	@OneToMany(mappedBy = "organization", cascade = { CascadeType.ALL })
	private List<Category> categoryList = new ArrayList<>();

	@OneToMany(mappedBy = "organization", cascade = { CascadeType.ALL })
	private List<Member> memberList = new ArrayList<>();

	public Organization(String id) {
		this.id = id;
	}
}
