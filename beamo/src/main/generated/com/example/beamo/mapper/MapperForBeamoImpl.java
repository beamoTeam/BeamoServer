package com.example.beamo.mapper;

import com.example.beamo.dto.menu.BasketMenuDto;
import com.example.beamo.dto.menu.BasketMenuDto.BasketMenuDtoBuilder;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.Basket.BasketBuilder;
import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.baskets.menu.BasketMenu.BasketMenuBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-30T16:27:14+0900",
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
        basketMenuDto.category( basketMenu.getCategory() );
        basketMenuDto.name( basketMenu.getName() );
        basketMenuDto.img( basketMenu.getImg() );
        basketMenuDto.price( basketMenu.getPrice() );
        basketMenuDto.count( basketMenu.getCount() );

        return basketMenuDto.build();
    }

    @Override
    public BasketMenu basketMenu_To_Entity(BasketMenuDto basketMenuDto) {
        if ( basketMenuDto == null ) {
            return null;
        }

        BasketMenuBuilder basketMenu = BasketMenu.builder();

        basketMenu.basket( basketMenuDtoToBasket( basketMenuDto ) );
        basketMenu.category( basketMenuDto.getCategory() );
        basketMenu.name( basketMenuDto.getName() );
        basketMenu.img( basketMenuDto.getImg() );
        basketMenu.price( basketMenuDto.getPrice() );
        basketMenu.count( basketMenuDto.getCount() );

        return basketMenu.build();
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
}
