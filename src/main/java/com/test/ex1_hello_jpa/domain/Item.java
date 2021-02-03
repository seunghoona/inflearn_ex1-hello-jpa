package com.test.ex1_hello_jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;


}
