package com.example.beamo.repository.baskets;

import com.example.beamo.repository.baskets.menu.BasketMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query(value = "select b.basketMenuList FROM Basket as b WHERE b.seq =?1")
    List<BasketMenu> findBasketMenuByU_seq(Long seq);

    @Query(value = "select b FROM Basket as b WHERE b.seq =?1")
    Basket findListBasketByB_seq(long seq);

    @Query(value = "select b from Basket as b WHERE b.seq =?1")
    Basket findB_seqByU_seq(Long seq);

}
