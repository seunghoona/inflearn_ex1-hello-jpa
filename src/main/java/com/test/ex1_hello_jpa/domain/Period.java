package com.test.ex1_hello_jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Period {
    private LocalDateTime stDateTime;
    private LocalDateTime enDateTime;
}
