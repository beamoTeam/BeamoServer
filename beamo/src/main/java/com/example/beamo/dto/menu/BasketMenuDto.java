package com.example.beamo.dto.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class BasketMenuDto {
    private Long seq;
    private short price;
    private short count;
    private Long basket_seq;
}
