package com.example.beamo.controller.restaurant;

import com.example.beamo.dto.menu.MenuDto;
import com.example.beamo.mapper.MapperForBeamo;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import com.example.beamo.repository.restaurants.menu.Menu;
import com.example.beamo.repository.restaurants.menu.MenuRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api/restaurant" , produces = "application/json")
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuRepository menuRepository;

    @ApiOperation(value = "음식점 전체 조회")
    @GetMapping
    public ResponseEntity getList() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return ResponseEntity.ok(restaurantList);
    }
    @ApiOperation(value = "음식점 전체 메뉴 출려 ")
    @GetMapping("/{r_seq}/menu")
    public ResponseEntity getMenu(@PathVariable("r_seq") Long seq) {
        List<Menu> byR_Seq = menuRepository.findByRestaurant(seq);
        List<MenuDto> dtoList = MapperForBeamo.INSTANCE.menu_To_List_DTO(byR_Seq);
        return ResponseEntity.ok(dtoList);
    }
}