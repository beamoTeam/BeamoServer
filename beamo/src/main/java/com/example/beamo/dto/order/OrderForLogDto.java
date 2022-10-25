package com.example.beamo.dto.order;

import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.chats.ChatInfo;
import com.example.beamo.repository.orders.Order;
import com.example.beamo.repository.orders.OrderRepository;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForLogDto {
    private Order order;
    private Restaurant restaurant;
    private ChatInfo chatInfo;
    private List<BasketMenu> basketMenuList = new ArrayList<>();
}
