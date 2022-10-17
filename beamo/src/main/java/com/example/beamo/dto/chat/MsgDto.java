package com.example.beamo.dto.chat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MsgDto {
        private String id;
        private String msg;
        private String sender;
        private String receiver;
        private Integer roomNum;

        private LocalDateTime createdAt;
}
