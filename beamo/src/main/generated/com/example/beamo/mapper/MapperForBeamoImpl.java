package com.example.beamo.mapper;

import com.example.beamo.dto.basket.BasketDto;
import com.example.beamo.dto.basket.BasketDto.BasketDtoBuilder;
import com.example.beamo.dto.menu.BasketMenuDto;
import com.example.beamo.dto.menu.BasketMenuDto.BasketMenuDtoBuilder;
import com.example.beamo.dto.menu.MenuDto;
import com.example.beamo.dto.menu.MenuDto.MenuDtoBuilder;
import com.example.beamo.dto.order.OrderDto;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.Basket.BasketBuilder;
import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.baskets.menu.BasketMenu.BasketMenuBuilder;
import com.example.beamo.repository.orders.Order;
import com.example.beamo.repository.orders.Order.OrderBuilder;
import com.example.beamo.repository.restaurants.Restaurant;
import com.example.beamo.repository.restaurants.Restaurant.RestaurantBuilder;
import com.example.beamo.repository.restaurants.menu.Menu;
import com.example.beamo.repository.restaurants.menu.Menu.MenuBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-05T19:08:20+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
public class MapperForBeamoImpl implements MapperForBeamo {

    @Override
    public BasketMenuDto basketMenu_To_DTO(BasketMenu basketMenu) {
        if ( basketMenu == null ) {
            return null;
        }

        BasketMenuDtoBuilder basketMenuDto = BasketMenuDto.builder();

        basketMenuDto.basket_seq( basketMenuBasketSeq( basketMenu ) );
        basketMenuDto.menu_seq( basketMenu.getMenu_seq() );
        basketMenuDto.category( basketMenu.getCategory() );
        basketMenuDto.name( basketMenu.getName() );
        basketMenuDto.img( basketMenu.getImg() );
        basketMenuDto.price( basketMenu.getPrice() );
        basketMenuDto.count( basketMenu.getCount() );
        basketMenuDto.restaurant_seq( basketMenu.getRestaurant_seq() );

        return basketMenuDto.build();
    }

    @Override
    public BasketMenu basketMenu_To_Entity(BasketMenuDto basketMenuDto) {
        if ( basketMenuDto == null ) {
            return null;
        }

        BasketMenuBuilder basketMenu = BasketMenu.builder();

        basketMenu.basket( basketMenuDtoToBasket( basketMenuDto ) );
        basketMenu.menu_seq( basketMenuDto.getMenu_seq() );
        basketMenu.category( basketMenuDto.getCategory() );
        basketMenu.name( basketMenuDto.getName() );
        basketMenu.img( basketMenuDto.getImg() );
        basketMenu.price( basketMenuDto.getPrice() );
        basketMenu.count( basketMenuDto.getCount() );
        basketMenu.restaurant_seq( basketMenuDto.getRestaurant_seq() );

        return basketMenu.build();
    }

    @Override
    public BasketDto basket_To_DTO(Basket basket) {
        if ( basket == null ) {
            return null;
        }

        BasketDtoBuilder basketDto = BasketDto.builder();

        basketDto.chatRoom( basket.getChatRoom() );
        basketDto.seq( basket.getSeq() );
        basketDto.deliveryPrice( basket.getDeliveryPrice() );
        basketDto.total_amount( basket.getTotal_amount() );

        return basketDto.build();
    }

    @Override
    public Basket basket_TO_Entity(BasketDto basketDto) {
        if ( basketDto == null ) {
            return null;
        }

        BasketBuilder basket = Basket.builder();

        basket.chatRoom( basketDto.getChatRoom() );
        basket.seq( basketDto.getSeq() );
        List<BasketMenu> list = basketDto.getBasketMenuList();
        if ( list != null ) {
            basket.basketMenuList( new ArrayList<BasketMenu>( list ) );
        }
        basket.deliveryPrice( basketDto.getDeliveryPrice() );
        basket.total_amount( basketDto.getTotal_amount() );

        return basket.build();
    }

    @Override
    public MenuDto menu_To_DTO(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDtoBuilder menuDto = MenuDto.builder();

        menuDto.restaurant_seq( menuRestaurantSeq( menu ) );
        menuDto.seq( menu.getSeq() );
        menuDto.category( menu.getCategory() );
        menuDto.name( menu.getName() );
        menuDto.img( menu.getImg() );
        menuDto.price( menu.getPrice() );
        menuDto.count( menu.getCount() );

        return menuDto.build();
    }

    @Override
    public List<MenuDto> menu_To_List_DTO(List<Menu> menus) {
        if ( menus == null ) {
            return null;
        }

        List<MenuDto> list = new ArrayList<MenuDto>( menus.size() );
        for ( Menu menu : menus ) {
            list.add( menu_To_DTO( menu ) );
        }

        return list;
    }

    @Override
    public Menu menu_To_Entity(MenuDto menuDto) {
        if ( menuDto == null ) {
            return null;
        }

        MenuBuilder menu = Menu.builder();

        menu.restaurant( menuDtoToRestaurant( menuDto ) );
        if ( menuDto.getSeq() != null ) {
            menu.seq( menuDto.getSeq() );
        }
        menu.category( menuDto.getCategory() );
        menu.name( menuDto.getName() );
        menu.img( menuDto.getImg() );
        menu.price( menuDto.getPrice() );
        menu.count( menuDto.getCount() );

        return menu.build();
    }

    @Override
    public OrderDto order_To_DTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        return orderDto;
    }

    @Override
    public Order order_To_Entity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        OrderBuilder order = Order.builder();

        return order.build();
    }

    private Long basketMenuBasketSeq(BasketMenu basketMenu) {
        if ( basketMenu == null ) {
            return null;
        }
        Basket basket = basketMenu.getBasket();
        if ( basket == null ) {
            return null;
        }
        long seq = basket.getSeq();
        return seq;
    }

    protected Basket basketMenuDtoToBasket(BasketMenuDto basketMenuDto) {
        if ( basketMenuDto == null ) {
            return null;
        }

        BasketBuilder basket = Basket.builder();

        if ( basketMenuDto.getBasket_seq() != null ) {
            basket.seq( basketMenuDto.getBasket_seq() );
        }

        return basket.build();
    }

    private Long menuRestaurantSeq(Menu menu) {
        if ( menu == null ) {
            return null;
        }
        Restaurant restaurant = menu.getRestaurant();
        if ( restaurant == null ) {
            return null;
        }
        long seq = restaurant.getSeq();
        return seq;
    }

    protected Restaurant menuDtoToRestaurant(MenuDto menuDto) {
        if ( menuDto == null ) {
            return null;
        }

        RestaurantBuilder restaurant = Restaurant.builder();

        if ( menuDto.getRestaurant_seq() != null ) {
            restaurant.seq( menuDto.getRestaurant_seq() );
        }

        return restaurant.build();
    }
}
