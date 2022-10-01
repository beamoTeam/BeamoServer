package com.example.beamo.repository.baskets;

import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.chats.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query(value = "select b.basketMenuList FROM Basket as b WHERE b.chatRoom =?1")
    List<BasketMenu> findBasketMenuByU_seq(ChatRoom chatRoom);

    @Query(value = "select b FROM Basket as b WHERE b.chatRoom.users.seq =?1")
    Basket findListBasketByU_seq(long seq);

    @Query(value = "select b.basketMenuList from Basket as b WHERE b.chatRoom.users.seq =?1 and b.chatRoom.chatInfo.seq=?2 ")
    List<BasketMenu> findMenuList_ByU_seqC_seq(Long u_seq, Long c_seq);

    @Query(value = "select b from Basket as b WHERE b.chatRoom=?1")
    Basket findByChatRoom(ChatRoom chatRoom);
}
