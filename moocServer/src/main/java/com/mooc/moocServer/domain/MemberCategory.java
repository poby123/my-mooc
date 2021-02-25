package com.mooc.moocServer.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MemberCategory {
    @Id
    @GeneratedValue
    @Column(name = "member_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
