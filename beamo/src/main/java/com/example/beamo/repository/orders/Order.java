package com.example.beamo.repository.orders;

import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.restaurants.Restaurant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne
    @JoinColumn(name = "restaurant_seq")
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "chat_room_seq"),
            @JoinColumn(name = "user_seq")
    })
    @JsonIgnore
    private ChatRoom chatRoom;

    private String payType;

    @Column(name = "pay_method")
    private String payMethod;

    @Column(name = "pay_status")
    private short payStatus;

    @Column(name = "pay_amount")
    private int payAmount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "pay_datetime", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime payDatetime;

    @Column(name = "total_status")
    private short totalStatus;

    @Column(name = "total_amount")
    private int totalAmount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_dt", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedDateTime;
}
