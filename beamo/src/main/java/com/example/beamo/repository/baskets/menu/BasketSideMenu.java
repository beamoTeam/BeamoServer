package com.example.beamo.repository.baskets.menu;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "basket_side_munu")
public class BasketSideMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String category;

    private String name;

    private short price;

    private short count;
}

