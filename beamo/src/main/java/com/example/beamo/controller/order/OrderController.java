package com.example.beamo.controller.order;

import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.BasketRepository;
import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.chats.ChatRoomRepository;
import com.example.beamo.repository.orders.Order;
import com.example.beamo.repository.orders.OrderRepository;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.RestaurantRepository;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    @ApiOperation(value = "유저번호로 주문 조회")
    @GetMapping("{u_seq}")
    public ResponseEntity getOrderByU_seq(@PathVariable("u_seq") Long seq) {
        return ResponseEntity.ok(orderRepository.findByU_seq(seq));
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

        Order order = Order.builder()
                .payAmount(basket.getTotal_amount())
                .payType("포인트")
                .chatRoom(chatRoom)
                .payStatus((short) 1)
                .totalStatus((short) 0)
                .restaurant(restaurant)
                .build();

        orderRepository.save(order);
            return ResponseEntity.ok(order);
        }
    }

    @ApiOperation(value = "시간 되면 자동 주문 현재는 버튼식")
    @GetMapping("total/{c_seq}")
    public ResponseEntity timetoOrder(@PathVariable("c_seq") Long seq) {

        List<Order> ls = orderRepository.findListByC_seq(seq);

        int count = 0;
        for( Order tmp : ls) {
            if(tmp.getPayStatus() == 1){
                count++;
            }
            if(tmp.getTotalStatus() == 1){
                return ResponseEntity.ok().body("주문 가능한 상태가 아님니다. 주문을 확인해주세요.");
            }
        }
        if (count >= 2){
            int totalPrice = 0;
            for( Order tmp : ls) {
                totalPrice += tmp.getPayAmount();
            }
            for( Order tmp : ls) {
                tmp.setTotalStatus((short) 1);
                tmp.setTotalAmount(totalPrice);
            }
            orderRepository.saveAll(ls);

            return ResponseEntity.ok(ls);

        }
        else {
            return ResponseEntity.ok().body("주문 가능한 상태가 아님니다. 정상적으로 주문되지 않았습니다. 주문을 확인해주세요.");
        }
    }

    @ApiOperation(value = "음식점 번호로 주문 조회")
    @GetMapping("/restaurant/{r_seq}")
    public ResponseEntity getOrderByR_seq(@PathVariable("r_seq") Long seq) {

        List<Order> ls = orderRepository.findListByR_seq(seq);

        List<Order> answer = new ArrayList<>();
        
        for( Order tmp : ls) {
            if(tmp.getTotalStatus() == 1){
                answer.add(tmp);
            }
        }
        
        return ResponseEntity.ok(answer);
    }
}
