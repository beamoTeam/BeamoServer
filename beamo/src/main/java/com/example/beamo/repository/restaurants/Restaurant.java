package com.example.beamo.repository.restaurants;

import com.example.beamo.repository.restaurants.menu.Menu;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @OneToMany
    @JoinColumn(name = "restaurant_seq")
    @JsonIgnore
    private List<Menu> menuList = new ArrayList<>();

    private String category;

    private String name;

    @Column(length = 300)
    private String img;

    @Column(name = "delivery_price")
    private short deliveryPrice;

    @Column(name = "max_member")
    private short maxMember;


    private int phone;

    private String adress;

    private DecimalFormat latitude;
    private DecimalFormat longitude;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_dt",updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedDateTime;
}
