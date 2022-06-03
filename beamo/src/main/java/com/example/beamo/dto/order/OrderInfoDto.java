package com.example.beamo.dto.order;

import com.example.beamo.repository.baskets.menu.BasketMenu;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderInfoDto {
    private String userName;
    private List<BasketMenu> basketMenuList = new ArrayList<>();
    private boolean pay_status;

    public OrderInfoDto addInfo(String userName, List<BasketMenu> bml, boolean x) {
        return OrderInfoDto.builder()
                .userName(userName)
                .basketMenuList(bml)
                .pay_status(x)
                .build();
    }
}

