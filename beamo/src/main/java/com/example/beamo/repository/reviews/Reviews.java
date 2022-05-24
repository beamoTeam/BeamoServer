package com.example.beamo.repository.reviews;

import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.restaurants.Restaurant;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "review")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "chat_room_seq"),
            @JoinColumn(name = "user_seq")
    })
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "restaurant_seq")
    private Restaurant restaurant;

    private float grade;
    private String content;

    @Column(name = "created_dt")
    private LocalDateTime createdDateTime;
    @Column(name = "updated_dt")
    private LocalDateTime updatedDateTime;
}
