package com.test.ex1_hello_jpa.domain;


import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    @Getter @Setter
    private Long id;

    @Column(name = "USERNAME")
    @Getter @Setter
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<Member_Product> member_products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();


}


