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
@Data
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")

    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();

    @Embedded
    private Period period;

    @Embedded
    private Adress adress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city"
                    ,column = @Column(name = "work_city")),
            @AttributeOverride(name = "zipcode"
                    ,column = @Column(name = "work_zipcode")),
            @AttributeOverride(name = "street"
                    ,column = @Column(name = "work_street")),

    })
    private Adress workAdress;

}


