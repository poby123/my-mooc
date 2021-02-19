package com.mooc.entity;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class ParticipantId implements Serializable {
	private static final long serialVersionUID = 15641349213L;
	private String member;
	private Long category;

	public ParticipantId(String member, Long category) {
		this.member = member;
		this.category = category;
	}

}
