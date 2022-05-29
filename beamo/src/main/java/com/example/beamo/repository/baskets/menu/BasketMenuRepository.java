package com.example.beamo.repository.baskets.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketMenuRepository extends JpaRepository<BasketMenu, Long> {
}
