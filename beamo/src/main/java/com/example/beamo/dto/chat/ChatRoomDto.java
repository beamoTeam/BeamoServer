package com.example.beamo.dto.chat;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {
    private Long users_seq;
    private Long chat_info_seq;
}
