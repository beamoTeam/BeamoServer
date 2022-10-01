package com.example.beamo.dto.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatInfoDto {
//    private String name;
    private String address;
    private String detail_address;
//    private DecimalFormat latitude;
//    private DecimalFormat longitude;
    private short maxPersonnel;
    private short currentMembers;

    @ApiModelProperty(example = "2022-06-04 20:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime orderTime;
    private long restaurant_seq;
}
