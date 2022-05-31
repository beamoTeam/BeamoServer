package com.example.beamo.repository.restaurants.menu;

import com.example.beamo.repository.restaurants.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query(value = "select r.menuList FROM Restaurant as r WHERE r.seq=?1")
    List<Menu> findByRestaurant(Long seq);

    @Query(value = "select m FROM Menu as m WHERE m.seq=?1")
    Menu findMenuBySeq(Long seq);
}

