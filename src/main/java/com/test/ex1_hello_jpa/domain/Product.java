package com.test.ex1_hello_jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Product {

    @Id
    private Long id;

    private String name;

}
