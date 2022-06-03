package com.example.beamo.dto.order;

import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.chats.ChatRoom;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMenuListDto {
    private long seq;

    private String restaurantName;

    private String userName;

    private final List<BasketMenu> basketMenuList = new ArrayList<>();

    private short deliveryPrice;

    private int total_amount;

    private int total_amount_with_delivery;

    private LocalDateTime payDatetime;

    private int totalAmount;

    private short totalStatus;
}
