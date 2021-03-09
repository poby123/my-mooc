package com.mooc.moocServer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Member {
    @Id
    @Column(name = "member_id")
    private String id;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_image")
    private String image;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    @JsonBackReference("organization-member")
    private Organization organization;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    @JsonManagedReference("member-board")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    // == 생성 메서드 == //
    public static Member createMember(String id, String password, Organization organization) {
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setImage("default image link");
        member.setRole(MemberRole.NONE);
        member.setOrganization(organization);

        return member;
    }

    public static Member createMember(String id, String password) {
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setImage("default image link");
        member.setRole(MemberRole.NONE);

        return member;
    }

    // == 연관관계 메서드 == //
    public void setOrganization(Organization organization){
        this.organization = organization;
    }

    public void addBoard(Board board) {
        this.boards.add(board);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
