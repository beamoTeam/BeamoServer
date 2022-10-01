package com.example.beamo.controller.basket;

import com.example.beamo.dto.basket.BasketDto;
import com.example.beamo.dto.menu.BasketMenuDto;
import com.example.beamo.dto.menu.MenuDto;
import com.example.beamo.mapper.MapperForBeamo;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.BasketRepository;
import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.baskets.menu.BasketMenuRepository;
import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.chats.ChatRoomRepository;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import com.example.beamo.repository.restaurants.menu.MenuRepository;
import com.example.beamo.service.users.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
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

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserService userService;

    MapperForBeamo mapperForBeamo;

//    @ApiOperation(value = "유저번호로 바스켓 내에 있는 메뉴 조회")
//    @GetMapping("/{u_seq}/menu")
//    public ResponseEntity getMenuInBasketByU_seq(@PathVariable("u_seq") Long seq) {
//        List<BasketMenu> anser = basketMenuRepository.findByU_seq(seq);
//        return ResponseEntity.ok(anser);
//    }

    @ApiOperation(value = "유저번호로 바스켓에 메뉴 넣기")
    @PostMapping("/{c_seq}")
    public ResponseEntity putMenuToBasket(HttpServletRequest request, @PathVariable("c_seq") Long c_seq,
                                          @RequestBody @NotNull MenuDto menuDto) {

        long u_seq = userService.getUser(request).getSeq();
        System.out.println(u_seq);

        ChatRoom chatRoom = chatRoomRepository.findByU_seqAndC_I_Seq(u_seq, c_seq);
        Basket lb = basketRepository.findByChatRoom(chatRoom);



        Long basket_seq = lb.getSeq();

        BasketMenuDto basketMenuDto = BasketMenuDto.builder()
                .menu_seq(menuDto.getSeq())
                .category(menuDto.getCategory())
                .name(menuDto.getName())
                .img(menuDto.getImg())
                .price(menuDto.getPrice())
                .count(menuDto.getCount())
                .basket_seq(basket_seq)
                .restaurant_seq(menuDto.getRestaurant_seq())
                .build();
        BasketMenu basketMenu = MapperForBeamo.INSTANCE.basketMenu_To_Entity(basketMenuDto);
        BasketMenuDto resultDto;
        try {
            basketMenuRepository.save(basketMenu);
            resultDto = MapperForBeamo.INSTANCE.basketMenu_To_DTO(basketMenu);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("바구니에 담기지 않았습니다. 다시 확인해주세요.");
        }
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(value = "유저번호로 바스켓 조회")
    @GetMapping("/{c_seq}")
    public ResponseEntity getBasketByU_seq(HttpServletRequest request, @PathVariable("c_seq") Long c_seq) {
        long u_seq = userService.getUser(request).getSeq();
        ChatRoom chatRoom = chatRoomRepository.findByU_seqAndC_I_Seq(u_seq, c_seq);
        Basket lb = basketRepository.findByChatRoom(chatRoom);

        lb.setTotal_amount(0);
        BasketDto basketDto = MapperForBeamo.INSTANCE.basket_To_DTO(lb);
        List<BasketMenu> ls = basketRepository.findBasketMenuByU_seq(chatRoom);

        basketDto.addBasMenuLS(ls);

        long restaurant_seq = 0;

        for(BasketMenu bb :ls){
            int price = bb.getCount()*bb.getPrice();
            basketDto.toTotal(price);
            restaurant_seq = bb.getRestaurant_seq();
        }

        if(ls.isEmpty() ){
            basketDto.setDeliveryPrice((short) 0);
        }
        else {
            short deliveryPrice = restaurantRepository.findBySeq(restaurant_seq).getDeliveryPrice();
            basketDto.setDeliveryPrice((short) 0);
            basketDto.setDeliveryPrice(deliveryPrice);
            basketDto.setTotal_amount_with_delivery(deliveryPrice+basketDto.getTotal_amount());
        }

        lb.setTotal_amount(basketDto.getTotal_amount());
        lb.setDeliveryPrice(basketDto.getDeliveryPrice());
        basketRepository.save(lb);

        return ResponseEntity.ok(basketDto);
    }

}
