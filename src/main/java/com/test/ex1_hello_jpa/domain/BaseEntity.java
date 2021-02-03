package com.test.ex1_hello_jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {
    private String createBy;
    private LocalDateTime CreateDate;
    private String lastModifyBy;
    private LocalDateTime lastModifyDate;
}
