package com.example.beamo.repository.restaurants.menu;

import com.example.beamo.repository.restaurants.Restaurant;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "munu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

//    @OneToMany
//    @JoinColumn(name = "menu_seq")
//    private List<SideMenu> sideMenuList = new ArrayList<>();

    private String category;

    private String name;

    @Column(length = 300)
    private String img;

    private short price;

    private short count;

    @ManyToOne
    @JoinColumn(name = "restaurant_seq")
    @JsonIgnore
    private Restaurant restaurant;
}
