package com.example.beamo.repository.restaurants.menu;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "side_munu")
public class SideMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String category;

    private String name;


    private short price;

    private short count;
}

