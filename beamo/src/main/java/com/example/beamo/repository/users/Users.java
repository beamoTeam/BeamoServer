package com.example.beamo.repository.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class Users {
    @Id
//    @Column(nullable = false)
    private Long seq;

    private String name;

    private String email;

    @Column(length = 300)
    private String profile;

    private int point = 1_000_000;

    @Column(name = "acount_name")
    private String acountName;

    @Column(name = "acount_num")
    private Integer acountNum;

    @Column(name = "user_role")
    private String userRole;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_dt", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedDateTime;
}
