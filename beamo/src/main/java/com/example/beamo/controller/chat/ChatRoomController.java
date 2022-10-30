package com.example.beamo.controller.chat;

import com.example.beamo.dto.chat.ChatInfoAddressDto;
import com.example.beamo.dto.chat.ChatInfoDto;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.BasketRepository;
import com.example.beamo.repository.chats.ChatInfo;
import com.example.beamo.repository.chats.ChatInfoRepository;
import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.chats.ChatRoomRepository;
import com.example.beamo.repository.orders.Order;
import com.example.beamo.repository.orders.OrderRepository;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import com.example.beamo.repository.restaurants.menu.MenuRepository;
import com.example.beamo.repository.users.Users;
import com.example.beamo.repository.users.UsersRepository;
import com.example.beamo.service.users.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = {"room-controller"})
@RequestMapping(value = "/api/room", produces = "application/json")
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

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;

    @ApiOperation(value = "모든 방 조회")
    @GetMapping
    public ResponseEntity getAllRoom() {
        return ResponseEntity.ok(chatInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "seq")));
    }

    @ApiOperation(value = "주소로 방 조회")
    @PostMapping("/address")
    public ResponseEntity getRoomByAddress(@RequestBody @NotNull ChatInfoAddressDto chatInfoAddressDto) {
        String address = chatInfoAddressDto.getAddress();
        return ResponseEntity.ok(chatInfoRepository.findByAddress(address, Sort.by(Sort.Direction.DESC, "seq")));
    }

    @ApiOperation(value = "JWT 유저번호로 방 만들기")
    @PostMapping
    public ResponseEntity makeChatRoomWhitU_seq(HttpServletRequest request, @RequestBody @NotNull ChatInfoDto chatInfoDto) {
        if (userService.getUser(request) == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }
        long seq = userService.getUser(request).getSeq();
        Users user = usersRepository.findBuU_seq(seq);

        if (user == null) {
            return ResponseEntity.badRequest().body("유저 정보가 없습니다. 확인해주세요.");
        } else {
            Restaurant restaurant = restaurantRepository.findBySeq(chatInfoDto.getRestaurant_seq());

            ChatInfo chatInfo = ChatInfo.builder()
                    .address(chatInfoDto.getAddress())
                    .detail_address(chatInfoDto.getDetail_address())
                    .name(restaurant.getName())
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

    @ApiOperation(value = "JWT 유저번호로 방 들어가기")
    @PostMapping("/{room_seq}")
    public ResponseEntity JoinChat(HttpServletRequest request, @PathVariable("room_seq") Long c_seq) {
        if (userService.getUser(request) == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }
        long u_seq = userService.getUser(request).getSeq();

        ChatRoom chatRoom = chatRoomRepository.findByU_seqAndC_I_Seq(u_seq, c_seq);
        if (chatRoom == null) {
            chatRoomRepository.saveComposite_Primary_Keys(u_seq, c_seq);
            ChatRoom cr = chatRoomRepository.findByU_seqAndC_I_Seq(u_seq, c_seq);
            Basket basket = Basket.builder()
                    .chatRoom(cr)
                    .build();
            basketRepository.save(basket);
        }
        return ResponseEntity.ok(chatInfoRepository.findBySeq(c_seq));
    }

    @ApiOperation(value = "JWT 유저번호, 방 번호 방 나가기")
    @GetMapping("/{room_seq}")
    public ResponseEntity leaveRoom(HttpServletRequest request, @PathVariable("room_seq") Long c_seq) {
        if (userService.getUser(request) == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }
        long seq = userService.getUser(request).getSeq();
        ChatRoom chatRoom = chatRoomRepository.findByU_seqAndC_I_Seq(seq, c_seq);
        Basket basket = basketRepository.findBasket_ByU_seqC_seq(seq, c_seq);
        if (basket != null) {
            System.out.println("basket not null");
            basketRepository.delete(basket);
            System.out.println("basket 삭제 완");
        }
        if (chatRoom != null) {
            System.out.println("chat not null");
            Order order = orderRepository.findByU_seqC_seq(seq, c_seq);
            if(order == null){
                chatRoomRepository.deleteByU_seqAndC_I_Seq(seq, c_seq);
                return new ResponseEntity<>("삭제 왼료", HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>("나가실 방이 없습니다.", HttpStatus.NOT_FOUND);
    }
}
