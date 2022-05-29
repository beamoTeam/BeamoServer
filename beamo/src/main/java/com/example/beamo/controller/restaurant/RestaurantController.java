package com.example.beamo.controller.restaurant;

import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import com.example.beamo.repository.restaurants.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/restaurant" , produces = "application/json")
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping
    public ResponseEntity getList() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return ResponseEntity.ok(restaurantList);
    }

    @GetMapping("/{seq}")
    public ResponseEntity getMenu(@PathVariable("seq") Long seq) {
        Optional<Restaurant> byId = restaurantRepository.findById(seq);
        return ResponseEntity.ok(byId);
    }
}