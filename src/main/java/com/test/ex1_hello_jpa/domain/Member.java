package com.test.ex1_hello_jpa.domain;


import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.Lock;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Data
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")

    private String username;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;


 /*   @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();*/

    @Embedded
    private Adress HomeAdress;

    @ElementCollection
    @CollectionTable(name = "FAVORTE_FOOD", joinColumns =
    @JoinColumn(name = "MEMBER_ID"))
    @Column(name="FOOD_NAME")
    private Set<String> favoriteFood = new HashSet<>();

/*    @ElementCollection
    @CollectionTable(name="address", joinColumns =
    @JoinColumn(name = "MEMBER_ID"))
    private List<Adress> adresses = new ArrayList<>();*/

    @OneToMany(cascade = ALL, orphanRemoval = true )
    @JoinColumn(name="MEMBER_ID")
    private List<AdressEntity> adresses = new ArrayList<>();

    @Embedded
    private Period period;

}


