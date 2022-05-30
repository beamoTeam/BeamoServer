package com.example.beamo.dto.restaurant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RestaurantDto {
    private String category;

    private String name;

    private String img;

    private short deliveryPrice;

    private short maxMember;


    private int phone;

    private String adress;

    private DecimalFormat latitude;
    private DecimalFormat longitude;

    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
