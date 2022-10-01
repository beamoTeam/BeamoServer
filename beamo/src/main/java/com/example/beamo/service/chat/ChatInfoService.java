package com.example.beamo.service.chat;

import com.example.beamo.dto.chat.ChatInfoDto;
import com.example.beamo.repository.chats.ChatInfo;
import com.example.beamo.repository.chats.ChatInfoRepository;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatInfoService {
    @Autowired
    ChatInfoRepository chatInfoRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public boolean plusCurrentMembers(ChatInfo chatInfoDto){
        ChatInfo tmpChatInfo = ChatInfo.builder()
                .seq(chatInfoDto.getSeq())
                .address(chatInfoDto.getAddress())
                .detail_address(chatInfoDto.getDetail_address())
                .maxPersonnel(chatInfoDto.getMaxPersonnel())
                .currentMembers((short) (chatInfoDto.getCurrentMembers() + 1))
                .name(chatInfoDto.getName())
                .orderTime(chatInfoDto.getOrderTime())
                .restaurant(chatInfoDto.getRestaurant())
                .build();
        chatInfoRepository.save(tmpChatInfo);
        return true;
    }
    public boolean minusCurrentMembers(ChatInfo chatInfoDto){
        ChatInfo tmpChatInfo = ChatInfo.builder()
                .seq(chatInfoDto.getSeq())
                .address(chatInfoDto.getAddress())
                .detail_address(chatInfoDto.getDetail_address())
                .maxPersonnel(chatInfoDto.getMaxPersonnel())
                .currentMembers((short) (chatInfoDto.getCurrentMembers() - 1))
                .name(chatInfoDto.getName())
                .orderTime(chatInfoDto.getOrderTime())
                .restaurant(chatInfoDto.getRestaurant())
                .build();
        chatInfoRepository.save(tmpChatInfo);
        return true;
    }
}
