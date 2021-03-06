package com.mooc.moocServer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Organization {
    @Id
    @Column(name = "organization_id")
    private String id;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    @JsonManagedReference("organization-category")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "organization")
    @JsonManagedReference("organization-member")
    private List<Member> members = new ArrayList<>();

    public Organization(String id){
        this.id = id;
    }

    // == 연관관계 메서드 == //
    public void addCategory(Category category){
        this.categories.add(category);
    }

    public void addMember(Member member){
        this.members.add(member);
    }

}
