package com.example.beamo.repository.baskets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query(value = "select b.basketMenuList FROM Basket as b WHERE b.chatRoom.users =?1")
    Basket findByU_seq(Long seq);

    @Query(value = "select b.seq from Basket as b WHERE b.chatRoom.users=?1")
    Long findB_seqByU_seq(Long seq);

}
