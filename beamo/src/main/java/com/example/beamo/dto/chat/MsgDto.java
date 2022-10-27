package com.example.beamo.dto.chat;

import com.example.beamo.repository.baskets.menu.BasketMenu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MsgDto {
    private String sender;
    private Integer roomNum;
    private List<BasketMenu> basketMenuList = new ArrayList<>();
}
