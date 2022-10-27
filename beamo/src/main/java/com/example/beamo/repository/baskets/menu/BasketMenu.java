package com.example.beamo.repository.baskets.menu;

import com.example.beamo.repository.baskets.Basket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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

    private long menu_seq;

//    @OneToMany
//    @JoinColumn(name = "basket_menu_seq")
//    private List<BasketSideMenu> BasketSideMenuList = new ArrayList<>();

    private String category;

    private String name;

    @Column(length = 300)
    private String img;

    private short price;

    private short count;

    private Long restaurant_seq;

    @ManyToOne
    @JoinColumn(name = "basket_seq")
    @JsonIgnore
    private Basket basket;
}
