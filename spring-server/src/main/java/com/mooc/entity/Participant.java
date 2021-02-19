package com.mooc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@IdClass(ParticipantId.class)
public class Participant {
	@Id
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	Participant(Category category, Member member) {
		this.category = category;
		this.member = member;
	}
}
