package com.example.beamo.repository.baskets.menu;

import com.example.beamo.repository.baskets.Basket;
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

//    @OneToMany
//    @JoinColumn(name = "basket_menu_seq")
//    private List<BasketSideMenu> BasketSideMenuList = new ArrayList<>();

    private String category;

    private String name;

    @Column(length = 300)
    private String img;

    private short price;

    private short count;

    @ManyToOne
    @JoinColumn(name = "basket_seq")
    private Basket basket;
}
