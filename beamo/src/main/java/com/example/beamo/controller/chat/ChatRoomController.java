package com.example.beamo.controller.chat;

import com.example.beamo.dto.chat.ChatInfoDto;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.BasketRepository;
import com.example.beamo.repository.chats.ChatInfo;
import com.example.beamo.repository.chats.ChatInfoRepository;
import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.chats.ChatRoomRepository;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import com.example.beamo.repository.restaurants.menu.MenuRepository;
import com.example.beamo.repository.users.Users;
import com.example.beamo.repository.users.UsersRepository;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/chatroom", produces = "application/json")
public class ChatRoomController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ChatInfoRepository chatInfoRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    MenuRepository menuRepository;


    @ApiOperation(value = "모든 방 조회")
    @GetMapping
    public ResponseEntity getAllRoom() {
        return ResponseEntity.ok(chatInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "seq")));
    }

    @ApiOperation(value = "유저번호로 방 만들기")
    @PostMapping("/{u_seq}")
    public ResponseEntity makeChatRoomWhitU_seq(@PathVariable("u_seq") Long seq, @RequestBody @NotNull ChatInfoDto chatInfoDto) {
        Users user = usersRepository.findBuU_seq(seq);

        if (user == null) {
            return ResponseEntity.badRequest().body("유저 정보가 없습니다. 확인해주세요.");
        } else {
            Restaurant restaurant = restaurantRepository.findBySeq(chatInfoDto.getRestaurant_seq());
            ChatInfo chatInfo = ChatInfo.builder()
                    .address(chatInfoDto.getAddress())
                    .detail_address(chatInfoDto.getDetail_address())
                    .maxPersonnel(chatInfoDto.getMaxPersonnel())
                    .name(restaurant.getName() +" - "+chatInfoDto.getDetail_address())
                    .orderTime(chatInfoDto.getOrderTime())
                    .restaurant(restaurant)
                    .build();
            long c_i_seq = chatInfoRepository.save(chatInfo).getSeq();

            ChatInfo findCI = chatInfoRepository.findBySeq(c_i_seq);

            chatRoomRepository.saveComposite_Primary_Keys(seq, c_i_seq);

            ChatRoom chatRoom = chatRoomRepository.findByU_seqAndC_I_Seq(seq, c_i_seq);
            Basket basket = Basket.builder()
                    .chatRoom(chatRoom)
                    .build();
            basketRepository.save(basket);

//            List<Menu> byR_Seq = menuRepository.findByRestaurant(restaurant.getSeq());
//            List<MenuDto> menuList = MapperForBeamo.INSTANCE.menu_To_List_DTO(byR_Seq);

            return ResponseEntity.ok(findCI);
        }
    }

    @ApiOperation(value = "유저번호로 방 들어가기")
    @PostMapping("/{u_seq}/{c_seq}")
    public ResponseEntity JoinChat(@PathVariable("u_seq") Long u_seq, @PathVariable("c_seq") Long c_seq) {

        ChatRoom chatRoom = chatRoomRepository.findByU_seqAndC_I_Seq(u_seq, c_seq);
        if (chatRoom == null) {
            ChatInfo ci = chatInfoRepository.findBySeq(c_seq);
            List<ChatRoom> roomList = chatRoomRepository.findByC_seq(c_seq);
            if (ci.getMaxPersonnel() <= roomList.size()) {
                return ResponseEntity.badRequest().body("인원 초과 입니다.");
            }

            chatRoomRepository.saveComposite_Primary_Keys(u_seq, c_seq);
            ChatRoom cr = chatRoomRepository.findByU_seqAndC_I_Seq(u_seq, c_seq);
            Basket basket = Basket.builder()
                    .chatRoom(cr)
                    .build();
            basketRepository.save(basket);

            return ResponseEntity.ok(chatInfoRepository.findBySeq(c_seq));
        }
        else
            return ResponseEntity.ok(chatInfoRepository.findBySeq(c_seq));
    }
}
