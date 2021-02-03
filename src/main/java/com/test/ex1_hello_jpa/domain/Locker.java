package com.test.ex1_hello_jpa.domain;


import javax.persistence.*;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    @Column(name="name")
    private String name;


}
