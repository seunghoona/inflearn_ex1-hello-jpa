package com.test.ex1_hello_jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@DiscriminatorColumn(name = "dType")
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;


}