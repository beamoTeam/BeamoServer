package com.example.beamo.repository.restaurants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
    @Query(value = "select r from Restaurant as r where r.seq=?1")
    Restaurant findBySeq(Long seq);
}
