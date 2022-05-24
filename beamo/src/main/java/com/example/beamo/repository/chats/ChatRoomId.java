package com.example.beamo.repository.chats;

import com.example.beamo.repository.users.Users;

import java.io.Serializable;

public class ChatRoomId implements Serializable {
    private long seq;
    private Users users;
}