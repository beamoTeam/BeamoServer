package com.example.beamo.dto.menu;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketMenuDto {
    private long menu_seq;
    private String category;
    private String name;
    private String img;
    private short price;
    private short count;
    private Long restaurant_seq;
    private Long basket_seq;
}
