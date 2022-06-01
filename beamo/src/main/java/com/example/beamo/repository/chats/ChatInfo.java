//package com.example.beamo.repository.chats;
//
//
//import com.example.beamo.repository.restaurants.Restaurant;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.*;
//
//import javax.persistence.*;
//import java.text.DecimalFormat;
//import java.time.LocalDateTime;
//
//
//@Entity
//@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//@AllArgsConstructor
//@Table(name = "chat_info")
//public class ChatInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long seq;
//
//
//
//    @OneToOne
//    @JoinColumn(name = "restaurant_seq")
//    private Restaurant restaurant;
//
//
//}