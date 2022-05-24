package com.example.beamo.repository.chats;


import com.example.beamo.repository.restaurants.Restaurant;
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

    @Column(name = "order_time")
    private LocalDateTime orderTime;
    @Column(name = "created_dt")
    private LocalDateTime createdDateTime;
    @Column(name = "updated_dt")
    private LocalDateTime updatedDateTime;

}