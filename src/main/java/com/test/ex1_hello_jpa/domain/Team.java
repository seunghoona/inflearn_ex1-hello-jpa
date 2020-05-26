package com.test.ex1_hello_jpa.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id ;
    private String name;

    // 양방형관계를 연결하기 위해서 아래와 같이 선언한다.
    // new ArrayList<>()는 관습적으로 사용되고 있다 그래야 Null이 안생김
    //mappedBy는 연결할 관계의 변수이름을 넣어주면된다
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
