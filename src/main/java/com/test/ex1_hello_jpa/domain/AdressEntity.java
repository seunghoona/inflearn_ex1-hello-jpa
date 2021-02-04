package com.test.ex1_hello_jpa.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
public class AdressEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Adress adress;

    public AdressEntity(String city, String street, String zipCode) {
        this.adress = new Adress(city, street, zipCode);
    }
}
