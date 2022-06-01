package com.example.beamo.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
    @Query(value = "select order from Order as order where order.chatRoom.users.seq=?1")
    Order findByU_seq(Long seq);

    @Query(value = "select order from Order as order where order.restaurant.seq=?1")
    Order findByR_seq(Long seq);

    @Query(value = "select order from Order as order where order.chatRoom.seq=?1")
    Order findByC_seq(Long seq);

    @Query(value = "select order from Order as order where order.chatRoom.seq=?1")
    List<Order> findListByC_seq(Long seq);

    @Query(value = "select order from Order as order where order.restaurant.seq=?1")
    List<Order> findListByR_seq(Long seq);

}
