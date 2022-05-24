package com.example.beamo.repository.users;

import javax.persistence.MappedSuperclass;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@MappedSuperclass
public class BaseUsers {
    @Id
    @Column(nullable = false)
    private long seq;

    private String name;

    @Column(length = 300)
    private String profile;

    private int point;

    @Column(name = "acount_name")
    private String acountName;

    @Column(name = "acount_num")
    private Integer acountNum;

    @Column(name = "created_dt")
    private LocalDateTime createdDateTime;
    @Column(name = "updated_dt")
    private LocalDateTime updatedDateTime;
}
