package com.test.ex1_hello_jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Album extends Item{

    private String arties;



}
