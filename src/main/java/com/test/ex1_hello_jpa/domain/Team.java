package com.test.ex1_hello_jpa.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    @Getter @Setter
    private Long id ;

    @Getter @Setter
    private String name;

    @Getter @Setter
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
