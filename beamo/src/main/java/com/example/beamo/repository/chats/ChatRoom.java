package com.example.beamo.repository.chats;

import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.users.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@IdClass(ChatRoomId.class)
@Table(name = "chat_room")
public class ChatRoom implements Serializable {
    @Id
    private Long seq;

    @Id
    @ManyToOne
    @JoinColumn(name = "users_seq")
    @JsonIgnore
    private Users users;

    @OneToOne
    @JoinColumn(name = "chat_info_seq")
    private ChatInfo chatInfo;
}


