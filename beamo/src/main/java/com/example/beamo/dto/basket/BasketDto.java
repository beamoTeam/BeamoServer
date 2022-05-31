package com.example.beamo.dto.basket;

import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.chats.ChatRoom;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class BasketDto {
    private long seq;
    private ChatRoom chatRoom;
    private final List<BasketMenu> basketMenuList = new ArrayList<>();
    private short deliveryPrice;
    private int total_amount;
    private int total_amount_with_delivery = 0;

    public void toTotal(int inputPrice) {
        this.total_amount += inputPrice;
    }

    public void addBasMenuLS(List<BasketMenu> ls) {
        this.basketMenuList.addAll(ls);
    }
}
