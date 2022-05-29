package com.example.beamo.controller.basket;

import com.example.beamo.dto.menu.BasketMenuDto;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.BasketRepository;
import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.baskets.menu.BasketMenuRepository;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api/basket" , produces = "application/json")
public class BasketController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    BasketMenuRepository basketMenuRepository;

    private final ModelMapper modelMapper;

    public BasketController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @PostMapping("/{u_seq}")
    public ResponseEntity CountMenu(@PathVariable("u_seq") Long seq,
                                    @RequestBody BasketMenuDto dto) {

//        Optional<Basket> basket = basketRepository.findById(seq);

//        List<BasketMenu> ma = (List<BasketMenu>) modelMapper.map(dto, BasketMenu.class);

//        basketMenuRepository.saveAll(ma);

//        basket.get().setBasketMenuList(ma);

        return ResponseEntity.ok(dto);
    }
}
