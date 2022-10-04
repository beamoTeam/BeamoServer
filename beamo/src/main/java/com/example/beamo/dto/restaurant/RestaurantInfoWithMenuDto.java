package com.example.beamo.dto.restaurant;

import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.menu.Menu;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantInfoWithMenuDto {
    List<Menu> menuList;
    Restaurant restaurant;
}
