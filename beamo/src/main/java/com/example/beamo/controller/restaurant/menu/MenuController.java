package com.example.beamo.controller.restaurant.menu;

import com.example.beamo.repository.restaurants.menu.Menu;
import com.example.beamo.repository.restaurants.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api/menu" , produces = "application/json")
public class MenuController {
    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/{seq}")
    public ResponseEntity getMenuByR_seq(@PathVariable("seq") Long seq) {
        List<Menu> byId = menuRepository.findByRestaurant(seq);
        return ResponseEntity.ok(byId);
    }
}
