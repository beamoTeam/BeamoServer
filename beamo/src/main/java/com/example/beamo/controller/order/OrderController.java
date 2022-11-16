package com.example.beamo.controller.order;

import com.example.beamo.dto.chat.MsgDto;
import com.example.beamo.dto.order.OrderForLogDto;
import com.example.beamo.dto.order.OrderInfoDto;
import com.example.beamo.dto.order.OrderMenuListDto;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.BasketRepository;
import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.baskets.menu.BasketMenuRepository;
import com.example.beamo.repository.chats.ChatInfo;
import com.example.beamo.repository.chats.ChatInfoRepository;
import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.chats.ChatRoomRepository;
import com.example.beamo.repository.orders.Order;
import com.example.beamo.repository.orders.OrderRepository;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import com.example.beamo.repository.users.Users;
import com.example.beamo.repository.users.UsersRepository;
import com.example.beamo.service.users.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping(value = "/api/order", produces = "application/json")
public class OrderController {

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatInfoRepository chatInfoRepository;
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BasketMenuRepository basketMenuRepository;

    @Autowired
    UserService userService;

    @ApiOperation(value = "JWT 유저번호로 주문 조회")
    @GetMapping
    public ResponseEntity getOrderByU_seq(HttpServletRequest request) {
        if (userService.getUser(request) == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }
        long seq = userService.getUser(request).getSeq();

        List<Order> ol = orderRepository.findListByU_seq(seq, Sort.by(Sort.Direction.DESC, "seq"));

        List<OrderForLogDto> logDtoList = new ArrayList<>();
        for (Order cr : ol) {
            OrderForLogDto orderLog = new OrderForLogDto();
            ChatRoom chatRoom = cr.getChatRoom();
            Long c_seq = chatRoom.getSeq();
            List<BasketMenu> bl = basketRepository.findMenuList_ByU_seqC_seq(seq, c_seq);
            orderLog.setOrder(orderRepository.findByChatRoom(chatRoom));
            orderLog.setRestaurant(cr.getRestaurant());
            orderLog.setChatInfo(chatRoom.getChatInfo());
            orderLog.setBasketMenuList(bl);

            logDtoList.add(orderLog);
        }
        return ResponseEntity.ok(logDtoList);
    }

    @ApiOperation(value = "JWT 유저번호로 바스켓 내용 결제 ")
    @GetMapping("/{room_seq}")
    public ResponseEntity orderByU_seq(HttpServletRequest request, @PathVariable("room_seq") Long c_seq) {
        if (userService.getUser(request) == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }
        long u_seq = userService.getUser(request).getSeq();

        ChatRoom chatRoom = chatRoomRepository.findByU_seqAndC_I_Seq(u_seq, c_seq);
        ChatInfo chatInfo = chatInfoRepository.findBySeq(chatRoom.getSeq());

        Basket basket = basketRepository.findByChatRoom(chatRoom);


        Restaurant restaurant = restaurantRepository.findBySeq(chatRoom.getChatInfo().getRestaurant().getSeq());

        if (basket == null) {
            return ResponseEntity.badRequest().body("장바구니가 비어있습니다.");
        } else {
            if (orderRepository.findByChatRoom(chatRoom) == null) {

                Order order = Order.builder()
                        .payAmount(basket.getTotal_amount())
                        .payType("포인트")
                        .chatRoom(chatRoom)
                        .payStatus((short) 1)
                        .totalStatus((short) 0)
                        .restaurant(restaurant)
                        .build();

                Users u = usersRepository.findBuU_seq(u_seq);
                if (u.getPoint() - basket.getTotal_amount() < 0) {
                    return ResponseEntity.badRequest().body("잔액이 부족합니다. 잔액을 확인해주세요");
                } else {
                    u.setPoint(u.getPoint() - basket.getTotal_amount());
                    orderRepository.save(order);
                    usersRepository.save(u);

                    List<Order> ls = orderRepository.findListByC_seq(c_seq);
                    if (ls.size() >= restaurant.getMaxMember()) {
                        chatInfo.setAbleToIn(false);
                        chatInfoRepository.save(chatInfo);

                        int totalAmount = 0;
                        for (Order tmp : ls) {
                            tmp.setPayMethod("접수 대기");
                            tmp.setTotalStatus((short) 1);
                            totalAmount += tmp.getPayAmount();
                        }
                        for (Order tmp : ls) {
                            tmp.setTotalAmount(totalAmount);
                        }
                        orderRepository.saveAll(ls);
                    }

                }
//                List<Order> ls = orderRepository.findListByC_seq(chatRoom.getSeq());
//                if(chatRoom.getChatInfo().getMaxPersonnel() == ls.size()) {
//                    int totalPrice = 0;
//                    for( Order tmp : ls) {
//                        totalPrice += tmp.getPayAmount();
//                    }
//                    for( Order tmp : ls) {
//                        tmp.setTotalStatus((short) 1);
//                        tmp.setPayMethod("접수 대기");
//                        tmp.setTotalAmount(totalPrice);
//                    }
//                    orderRepository.saveAll(ls);
//                }
                List<BasketMenu> bl = basketRepository.findMenuList_ByU_seqC_seq(u_seq, c_seq);
                MsgDto msgDto = new MsgDto();
                msgDto.setSender(usersRepository.findBuU_seq(u_seq).getName());
                msgDto.setRoomNum(c_seq.intValue());
                msgDto.setBasketMenuList(bl);
                return ResponseEntity.ok(msgDto);
            }
            return ResponseEntity.ok().body("이미 결제되었습니다. 다시 확인하세요.");
        }
    }

    @ApiOperation(value = "2명 이상인 주문 전체 주문 버튼")
    @GetMapping("total/{room_seq}")
    public ResponseEntity timetoOrder(@PathVariable("room_seq") Long seq) {

        List<Order> ls = orderRepository.findListByC_seq(seq);

        Restaurant restaurant = Restaurant.builder().build();
        LocalDateTime payDatetime = null;
        String address = "";

        OrderInfoDto orderInfoDto = new OrderInfoDto();
        List<OrderInfoDto> oil = new ArrayList<>();
        List<BasketMenu> bml = new ArrayList<>();
        List<OrderMenuListDto> omlList = new ArrayList<>();

        List<Long> list = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        int count = 0;
        int totalPrice = 0;

        for (Order tmp : ls) {
            if (tmp.getPayStatus() == 1) {
                count++;
            }
//            if(tmp.getTotalStatus() == 1){
//                return ResponseEntity.ok().body("주문 가능한 상태가 아님니다. 주문을 확인해주세요.");
//            }
        }
        if (count >= 2) {
            for (Order tmp : ls) {
                totalPrice += tmp.getPayAmount();
            }
            for (Order tmp : ls) {
                tmp.setTotalStatus((short) 1);
                tmp.setTotalAmount(totalPrice);
                restaurant = tmp.getRestaurant();
                payDatetime = tmp.getUpdatedDateTime();
                address = tmp.getChatRoom().getChatInfo().getAddress();
            }
            List<Order> savedOrders = orderRepository.saveAll(ls);

            for (Order tmp : ls) {
                nameList.add(tmp.getChatRoom().getUsers().getName());
                list.add(tmp.getChatRoom().getUsers().getSeq());
            }

            for (long i = 0; i < list.size(); i++) {
                bml = basketMenuRepository.findMLUC(list.get((int) i), seq);
                oil.add(orderInfoDto.addInfo(nameList.get((int) i), bml, true));
            }

            OrderMenuListDto oml = OrderMenuListDto.builder()
                    .restaurantName(restaurant.getName())
                    .totalAmount(totalPrice)
                    .payDatetime(payDatetime)
                    .UserOrderList(oil)
                    .address(address)
                    .build();

            omlList.add(oml);

            return ResponseEntity.ok(omlList);
        } else {
            return ResponseEntity.ok().body("주문 가능한 상태가 아님니다. 정상적으로 주문되지 않았습니다. 주문을 확인해주세요.");
        }
    }

    @ApiOperation(value = "음식점 번호로 주문 조회")
    @GetMapping("/restaurant/{r_seq}")
    public ResponseEntity getOrderByR_seq(@PathVariable("r_seq") Long seq) {

        List<OrderMenuListDto> omlList = new ArrayList<>();

        Set set = new HashSet();

        OrderInfoDto orderInfoDto = new OrderInfoDto();
        List<BasketMenu> bml = new ArrayList<>();
        List<Order> orderList = orderRepository.findListByR_seq(seq);


        for (Order tmp : orderList) {
            set.add(tmp.getChatRoom().getSeq());
        }

        for (Object chatNum : set) {
            List<OrderInfoDto> oil = new ArrayList<>();
            int totalPrice = 0;
            LocalDateTime payDatetime = null;
            String address = "";
            String accepted = "";
            long c_seq = 0;
            List<Order> ls = orderRepository.findListByC_seq((Long) chatNum);

            boolean pass = false;
            for (Order tmp : ls) {
                if (tmp.getTotalStatus() == 1) {
                    totalPrice = tmp.getTotalAmount();
                    payDatetime = tmp.getUpdatedDateTime();
                    address = tmp.getChatRoom().getChatInfo().getAddress();
                    accepted = tmp.getPayMethod();
                    c_seq = (long) chatNum;
                    bml = basketMenuRepository.findMLUC(tmp.getChatRoom().getUsers().getSeq(), (Long) chatNum);
                    oil.add(orderInfoDto.addInfo(tmp.getChatRoom().getUsers().getName(), bml, true));
                } else {
                    pass = true;
                }
            }
            if (pass == false) {
                OrderMenuListDto oml = OrderMenuListDto.builder()
                        .c_seq(c_seq)
                        .restaurantName(restaurantRepository.findBySeq(seq).getName())
                        .totalAmount(totalPrice)
                        .payDatetime(payDatetime)
                        .UserOrderList(oil)
                        .address(address)
                        .accepted(accepted)
                        .build();
                omlList.add(oml);
            }
        }

        Collections.reverse(omlList);
        return ResponseEntity.ok(omlList);
    }

    @ApiOperation(value = "주문 접수 상태 변경")
    @GetMapping("/accepted/{room_seq}")
    public ResponseEntity getAcceptedByC_seq(@PathVariable("room_seq") Long seq) {
        List<Order> ls = orderRepository.findListByC_seq(seq);
        for (Order tmp : ls) {
            tmp.setPayMethod("접수 완료");
        }
        orderRepository.saveAll(ls);
        return ResponseEntity.ok().build();
    }
}
