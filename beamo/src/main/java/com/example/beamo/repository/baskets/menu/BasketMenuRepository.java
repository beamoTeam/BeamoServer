package com.example.beamo.repository.baskets.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketMenuRepository extends JpaRepository<BasketMenu, Long> {
    @Query(value = "select b FROM BasketMenu as b WHERE b.basket.chatRoom.users.seq=?1")
    List<BasketMenu> findByU_seq(Long seq);


}
