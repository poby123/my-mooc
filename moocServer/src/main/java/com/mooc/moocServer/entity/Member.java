package com.mooc.moocServer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {
    @Id
    @Column(name = "member_id", nullable = false, unique = true, length = 30)
    private String id;

    @Column(name = "member_password", nullable = false, length = 100)
    private String password;

    @Column(name = "member_image")
    private String image;

//    @Enumerated(EnumType.STRING)
//    private MemberRole roles;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    @JsonBackReference("organization-member")
    private Organization organization;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    @JsonManagedReference("member-board")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    @JsonManagedReference("member-comment")
    private List<Comment> comments = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.getId();
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isEnabled(){
        return true;
    }



    // == 생성 메서드 == //
    public static Member createMember(String id, String password, Organization organization) {
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setImage("default image link");
//        member.setRoles(MemberRole.NONE);
        member.setOrganization(organization);

        return member;
    }

    public static Member createMember(String id, String password) {
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setImage("default image link");
        member.setRoles(Collections.singletonList("ROLE_USER"));

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
