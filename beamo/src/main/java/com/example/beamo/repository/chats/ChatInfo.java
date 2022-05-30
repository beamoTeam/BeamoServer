package com.example.beamo.repository.chats;


import com.example.beamo.repository.restaurants.Restaurant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "chat_info")
public class ChatInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String name;

    private String adress;

    private DecimalFormat latitude;
    private DecimalFormat longitude;

    @OneToOne
    @JoinColumn(name = "restaurant_seq")
    private Restaurant restaurant;


    private short personnel;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "order_time")
    private LocalDateTime orderTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_dt",updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedDateTime;

}