package com.example.beamo.repository.owvers;

import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.users.BaseUsers;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
public class Owners extends BaseUsers {
    @OneToMany
    @JoinColumn(name = "owner_seq")
    private List<Restaurant> restaurantList = new ArrayList<>();
}