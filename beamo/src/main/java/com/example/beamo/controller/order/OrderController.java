package com.example.beamo.controller.order;

import com.example.beamo.dto.order.OrderInfoDto;
import com.example.beamo.dto.order.OrderMenuListDto;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.BasketRepository;
import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.baskets.menu.BasketMenuRepository;
import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.chats.ChatRoomRepository;
import com.example.beamo.repository.orders.Order;
import com.example.beamo.repository.orders.OrderRepository;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import com.example.beamo.repository.users.Users;
import com.example.beamo.repository.users.UsersRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/api/order" , produces = "application/json")
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
    UsersRepository usersRepository;

    @Autowired
    BasketMenuRepository basketMenuRepository;

    @ApiOperation(value = "유저번호로 주문 조회")
    @GetMapping("{u_seq}")
    public ResponseEntity getOrderByU_seq(@PathVariable("u_seq") Long seq) {
        return ResponseEntity.ok(orderRepository.findListByU_seq(seq));
    }

    @ApiOperation(value = "유저번호로 바스켓 내용 그대로 주문 넣기")
    @PostMapping("/{u_seq}/{c_seq}")
    public ResponseEntity orderByU_seq(@PathVariable("u_seq") Long u_seq, @PathVariable("c_seq") Long c_seq) {

        ChatRoom chatRoom = chatRoomRepository.findByU_seqAndC_I_Seq(u_seq, c_seq);
        Basket basket = basketRepository.findByChatRoom(chatRoom);

        Restaurant restaurant = restaurantRepository.findBySeq(chatRoom.getChatInfo().getRestaurant().getSeq());

        if (basket == null) {
            return ResponseEntity.badRequest().body("장바구니가 비어있습니다.");
        }
        else {
            if(orderRepository.findByChatRoom(chatRoom) == null) {

                Order order = Order.builder()
                        .payAmount(basket.getTotal_amount())
                        .payType("포인트")
                        .chatRoom(chatRoom)
                        .payStatus((short) 1)
                        .totalStatus((short) 0)
                        .restaurant(restaurant)
                        .build();
                orderRepository.save(order);

                Users u = usersRepository.findBuU_seq(u_seq);
                if (u.getPoint()-basket.getTotal_amount() < 0 ) {
                    return ResponseEntity.badRequest().body("잔액이 부족합니다. 잔액을 확인해주세요");
                }
                else {
                    u.setPoint(u.getPoint()-basket.getTotal_amount());
                    usersRepository.save(u);
                }
                List<Order> ls = orderRepository.findListByC_seq(chatRoom.getSeq());
                if(chatRoom.getChatInfo().getMaxPersonnel() == ls.size()) {
                    int totalPrice = 0;
                    for( Order tmp : ls) {
                        totalPrice += tmp.getPayAmount();
                    }
                    for( Order tmp : ls) {
                        tmp.setTotalStatus((short) 1);
                        tmp.setPayMethod("접수 대기");
                        tmp.setTotalAmount(totalPrice);
                    }
                    orderRepository.saveAll(ls);
                }

                return ResponseEntity.ok(order);
            }

            return ResponseEntity.ok().body("이미 결제되었습니다. 다시 확인하세요.");

        }
    }

    @ApiOperation(value = "시간 되면 자동 주문 현재는 버튼식")
    @GetMapping("total/{c_seq}")
    public ResponseEntity timetoOrder(@PathVariable("c_seq") Long seq) {

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

        for( Order tmp : ls) {
            if(tmp.getPayStatus() == 1){
                count++;
            }
//            if(tmp.getTotalStatus() == 1){
//                return ResponseEntity.ok().body("주문 가능한 상태가 아님니다. 주문을 확인해주세요.");
//            }
        }
        if (count >= 2){
            for( Order tmp : ls) {
                totalPrice += tmp.getPayAmount();
            }
            for( Order tmp : ls) {
                tmp.setTotalStatus((short) 1);
                tmp.setTotalAmount(totalPrice);
                restaurant = tmp.getRestaurant();
                payDatetime = tmp.getUpdatedDateTime();
                address = tmp.getChatRoom().getChatInfo().getAddress();
            }
            List<Order> savedOrders = orderRepository.saveAll(ls);

            for( Order tmp : ls) {
                nameList.add(tmp.getChatRoom().getUsers().getName());
                list.add(tmp.getChatRoom().getUsers().getSeq());
            }

            for( long i = 0 ; i<list.size() ; i++) {
                bml = basketMenuRepository.findMLUC(list.get((int) i),seq);
                oil.add( orderInfoDto.addInfo( nameList.get((int) i), bml, true) );
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
        }
        else {
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


        for(Order tmp : orderList) {
            set.add(tmp.getChatRoom().getSeq());
        }

        for(Object chatNum : set) {
            List<OrderInfoDto> oil = new ArrayList<>();
            int totalPrice = 0;
            LocalDateTime payDatetime = null;
            String address = "";
            String accepted = "";
            long c_seq = 0;
            List<Order> ls = orderRepository.findListByC_seq((Long) chatNum);

            boolean pass = false;
            for( Order tmp : ls) {
                if(tmp.getTotalStatus()==1){
                    totalPrice = tmp.getTotalAmount();
                    payDatetime = tmp.getUpdatedDateTime();
                    address = tmp.getChatRoom().getChatInfo().getAddress();
                    accepted = tmp.getPayMethod();
                    c_seq = (long) chatNum;
                    bml = basketMenuRepository.findMLUC(tmp.getChatRoom().getUsers().getSeq(), (Long) chatNum);
                    oil.add( orderInfoDto.addInfo( tmp.getChatRoom().getUsers().getName(), bml, true) );
                }else {
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

        return ResponseEntity.ok(omlList);
    }

    @ApiOperation(value = "주문 접수 상태 변경")
    @GetMapping("/accepted/{c_seq}")
    public ResponseEntity getAcceptedByC_seq(@PathVariable("c_seq") Long seq) {
        List<Order> ls = orderRepository.findListByC_seq(seq);
        boolean pass = false;
        for( Order tmp : ls) {
            if(tmp.getPayMethod() == "접수 대기") {
                tmp.setPayMethod("접수 완료");
                pass = true;
            }
        }
        if (pass == true){
            orderRepository.saveAll(ls);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().body("이미 접수 완료 된 상태 입니다. 다시 확인해주세요.");
        }
    }
}
