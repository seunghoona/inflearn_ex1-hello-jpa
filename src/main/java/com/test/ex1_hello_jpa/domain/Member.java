package com.test.ex1_hello_jpa.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data

@SequenceGenerator(name ="memeber_seq_generator", sequenceName = "member_seq")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "memeber_seq_generator")
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
