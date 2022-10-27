package com.example.beamo.service.restaurant;

import com.example.beamo.dto.restaurant.RestaurantInfoWithMenuDto;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import com.example.beamo.repository.restaurants.menu.Menu;
import com.example.beamo.repository.restaurants.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuRepository menuRepository;

    public RestaurantInfoWithMenuDto getRML(Long seq) {
        Restaurant restaurant = restaurantRepository.findBySeq(seq);
        List<Menu> menuList = menuRepository.findByRestaurant(seq);
        RestaurantInfoWithMenuDto dto = new RestaurantInfoWithMenuDto();
        dto.setMenuList(menuList);
        dto.setRestaurant(restaurant);

        return dto;
    }
}
