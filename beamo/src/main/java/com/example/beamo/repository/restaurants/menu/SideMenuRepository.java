package com.example.beamo.repository.restaurants.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SideMenuRepository extends JpaRepository<SideMenu, Long> {
}
