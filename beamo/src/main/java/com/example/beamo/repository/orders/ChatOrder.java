//package com.example.beamo.repository.orders;
//
//import com.example.beamo.repository.chats.ChatRoom;
//import com.example.beamo.repository.restaurants.Restaurant;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//@Setter
//@AllArgsConstructor
//@Table(name = "chat_orders")
//public class ChatOrder {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long seq;
//
//    @OneToMany
//    @JoinColumn(name = "order_seq")
//    @JsonIgnore
//    private List<Order> basketMenuList = new ArrayList<>();
//
//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "chat_room_seq"),
//            @JoinColumn(name = "user_seq")
//    })
//    @JsonIgnore
//    private ChatRoom chatRoom;
//
//    @ManyToOne
//    @JoinColumn(name = "restaurant_seq")
//    @JsonIgnore
//    private Restaurant restaurant;
//
//
//}
