package com.example.beamo.dto.restaurant;

import com.example.beamo.repository.restaurants.menu.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class RestaurantDto {
    private String category;

    private String name;

    private String img;

    private short deliveryPrice;

    private short maxMember;

    private short minPrice;

    private float rating;

    private List<Menu> menuList = new ArrayList<>();

    private int phone;

    private String address;

    private DecimalFormat latitude;
    private DecimalFormat longitude;

    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
