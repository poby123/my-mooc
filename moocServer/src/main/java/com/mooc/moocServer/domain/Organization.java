package com.mooc.moocServer.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Organization {
    @Id
    @Column(name = "organization_id")
    private String id;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "organization")
    private List<Member> members = new ArrayList<>();
}
