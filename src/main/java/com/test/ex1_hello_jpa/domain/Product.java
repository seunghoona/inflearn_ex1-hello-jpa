package com.test.ex1_hello_jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "product")
    private List<Member_Product> member_products = new ArrayList<>();

}
