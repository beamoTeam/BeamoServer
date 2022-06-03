package com.example.beamo.dto.chat;

import com.example.beamo.repository.chats.ChatInfo;
import com.example.beamo.repository.users.Users;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {
    private Long users_seq;
    private Long chat_info_seq;
}
