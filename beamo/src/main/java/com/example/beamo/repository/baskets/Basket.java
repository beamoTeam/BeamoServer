package com.example.beamo.repository.baskets;

import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.chats.ChatRoom;
import com.example.beamo.repository.restaurants.menu.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "chat_room_seq"),
            @JoinColumn(name = "user_seq")
    })
    private ChatRoom chatRoom;

    @OneToMany
    @JoinColumn(name = "basket_seq")
    @JsonIgnore
    private List<BasketMenu> basketMenuList = new ArrayList<>();

    private short count;
    private int total_amount;

    public void calculate() {
        for (int i =0; i<basketMenuList.size();i++){
            if(basketMenuList.get(i).getCount()>0) {
                total_amount += ( (basketMenuList.get(i).getCount()) * (basketMenuList.get(i).getPrice()) );
                count += basketMenuList.get(i).getCount();
            }
        }
    }
}
