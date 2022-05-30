package com.example.beamo.controller.basket;

import com.example.beamo.dto.menu.BasketMenuDto;
import com.example.beamo.dto.menu.MenuDto;
import com.example.beamo.mapper.MapperForBeamo;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.BasketRepository;
import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.baskets.menu.BasketMenuRepository;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import com.example.beamo.repository.restaurants.menu.MenuRepository;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/basket" , produces = "application/json")
public class BasketController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    BasketMenuRepository basketMenuRepository;

    @Autowired
    MenuRepository menuRepository;


     ModelMapper modelMapper;

    @ApiOperation(value = "유저번호로 바스켓 내에 있는 메뉴 조회")
    @GetMapping("/{u_seq}/menu")
    public ResponseEntity getMenuInBasketByU_seq(@PathVariable("u_seq") Long seq) {
        List<BasketMenu> anser = basketMenuRepository.findByU_seq(seq);
        return ResponseEntity.ok(anser);
    }

    @ApiOperation(value = "유저번호로 바스켓 내에 있는 메뉴 넣기")
    @PostMapping("/{u_seq}/menu")
    public ResponseEntity testMenuDto(@PathVariable("u_seq") Long seq,
                                      @RequestBody @NotNull MenuDto menuDto) {
        Long basket_seq = basketRepository.findById(seq).get().getSeq();

        BasketMenuDto basketMenuDto = BasketMenuDto.builder()
                .category(menuDto.getCategory())
                .name(menuDto.getName())
                .img(menuDto.getImg())
                .price(menuDto.getPrice())
                .count(menuDto.getCount())
                .basket_seq(basket_seq)
                .build();
        BasketMenu basketMenu = MapperForBeamo.INSTANCE.basketMenu_To_Entity(basketMenuDto);
        BasketMenuDto resultDto;
        try {
            basketMenuRepository.save(basketMenu);
            resultDto = MapperForBeamo.INSTANCE.basketMenu_To_DTO(basketMenu);
        }
        catch (Exception e) {
            resultDto = null;
        }
        return ResponseEntity.ok(resultDto);
    }
//      수정할 부분
    @ApiOperation(value = "유저번호로 바스켓 조회")
    @GetMapping("/{u_seq}")
    public ResponseEntity getBasketByU_seq(@PathVariable("u_seq") Long seq) {
        List<BasketMenu> ls = basketRepository.findBasketMenuByU_seq(seq);
        Basket lb = basketRepository.findListBasketByB_seq(seq);
        lb.setCount((short) 0);
        lb.setTotal_amount(0);
        for( BasketMenu menu : ls ){
            lb.addCoount(menu.getCount(),menu.getPrice());
        }
        basketRepository.save(lb);
//        Basket anseer = basketRepository.findB_seqByU_seq(seq);
//        anseer.calculate();
//        Basket newBasket = basketRepository.save(anseer);
        return ResponseEntity.ok(lb);
    }
}
