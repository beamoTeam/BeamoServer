package com.example.beamo.repository.chats;

import com.example.beamo.repository.users.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "chat_room")
@IdClass(ChatRoomId.class)
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_seq")
    private Users users;

    @OneToOne
    @JoinColumn(name = "chat_info_seq")
    private ChatInfo chat_info;



    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_dt",updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedDateTime;
}


