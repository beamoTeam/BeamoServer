package com.example.beamo.dto.menu;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private String category;
    private String name;
    private String img;
    private short price;
    private short count;
    private Long restaurant_seq;
}
