package com.example.beamo.repository.orders;

import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.restaurants.Restaurant;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne
    @JoinColumn(name = "restaurant_seq")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "chat_room_seq"),
            @JoinColumn(name = "user_seq")
    })
    private ChatRoom chatRoom;

    private String payType;

    @Column(name = "pay_method")
    private String payMethod;

    @Column(name = "pay_status")
    private short payStatus;

    @Column(name = "pay_datetime")
    private LocalDateTime payDatetime;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "created_dt")
    private LocalDateTime createdDateTime;
    @Column(name = "updated_dt")
    private LocalDateTime updatedDateTime;

}
