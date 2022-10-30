package com.example.beamo.repository.orders;

import com.example.beamo.repository.chats.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select order from Order as order where order.chatRoom.users.seq=?1")
    Order findByU_seq(Long seq);

    @Query(value = "select order from Order as order where order.restaurant.seq=?1")
    Order findByR_seq(Long seq);

    @Query(value = "select order from Order as order where order.chatRoom.seq=?1")
    Order findByC_seq(Long seq);

    @Query(value = "select order from Order as order where order.chatRoom.users.seq=?1 and order.chatRoom.seq=?2")
    Order findByU_seqC_seq(Long seq, Long c_seq);

    @Query(value = "select order from Order as order where order.chatRoom.seq=?1")
    List<Order> findListByC_seq(Long seq);

    @Query(value = "select order from Order as order where order.chatRoom.users.seq=?1")
    List<Order> findListByU_seq(Long seq);

    @Query(value = "select order from Order as order where order.restaurant.seq=?1")
    List<Order> findListByR_seq(Long seq);

    @Query(value = "select order.chatRoom from Order as order where order.restaurant.seq=?1 and order.totalStatus=1")
    List<ChatRoom> findChatRoomByR_seq(Long seq);

    @Query(value = "select b from Order as b WHERE b.chatRoom=?1")
    Order findByChatRoom(ChatRoom chatRoom);


    @Query(value = "select order.chatRoom.seq from Order as order where order.restaurant.seq=?1")
    List<Long> findC_seqListByR_seq(Long seq);
}
