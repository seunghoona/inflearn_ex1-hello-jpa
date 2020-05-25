package com.test.ex1_hello_jpa.domain;


import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;
import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data

@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "Member_SEQ",
        initialValue = 1, allocationSize = 50)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private long id;





    @Column(name = "name")
    private String userName;

    private Integer age;

    //ENUM 클래스사용
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    //Date,Calendal를 사용시 아래와 같은 방법으로 처리
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;


    //1.8부터는
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String descroption;

}
