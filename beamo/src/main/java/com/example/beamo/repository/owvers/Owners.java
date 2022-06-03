package com.example.beamo.repository.owvers;

import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.users.BaseUsers;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
@Table(name = "owner")
public class Owners extends BaseUsers {
    @Id
    @Column(nullable = false)
    private long seq;

    @OneToMany
    @JoinColumn(name = "owner_seq")
    private List<Restaurant> restaurantList = new ArrayList<>();
}