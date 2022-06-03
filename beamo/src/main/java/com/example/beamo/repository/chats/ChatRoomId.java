package com.example.beamo.repository.chats;

import com.example.beamo.repository.users.Users;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ChatRoomId implements Serializable {
    private Long seq;
    private Users users;
}