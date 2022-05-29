package com.example.beamo.repository.baskets.menu;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "basket_munu")
public class BasketMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @OneToMany
    @JoinColumn(name = "basket_menu_seq")
    private List<BasketSideMenu> BasketSideMenuList = new ArrayList<>();

    private short price;

    private short count;
}
