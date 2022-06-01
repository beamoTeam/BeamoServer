package com.example.beamo.dto.order;

import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.restaurants.Restaurant;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class OrderDto {
    private long seq;

    private long restaurant_seq;

    private ChatRoom chatRoom;

    private String payType;

    private String payMethod;

    private short payStatus;

    private int payAmount;

    private LocalDateTime payDatetime;

    private int totalAmount;

    private short totalStatus;

}
