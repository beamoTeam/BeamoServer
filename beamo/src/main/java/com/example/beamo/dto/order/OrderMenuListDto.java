package com.example.beamo.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderMenuListDto {
    private String restaurantName;

    private String address;

    List<OrderInfoDto> UserOrderList = new ArrayList<>();

    private LocalDateTime payDatetime;

    private long c_seq;
    private String accepted;
    private int totalAmount;
}
