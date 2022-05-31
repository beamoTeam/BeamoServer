package com.example.beamo.mapper;

import com.example.beamo.dto.basket.BasketDto;
import com.example.beamo.dto.menu.BasketMenuDto;
import com.example.beamo.dto.menu.MenuDto;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.menu.BasketMenu;
import com.example.beamo.repository.restaurants.menu.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.MappingControl;
import org.mapstruct.factory.Mappers;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperForBeamo {

    MapperForBeamo INSTANCE = Mappers.getMapper(MapperForBeamo.class);

    // basketMenu
    @Mapping(target = "basket_seq", source = "basket.seq")
    BasketMenuDto basketMenu_To_DTO(BasketMenu basketMenu);
    @Mapping(target = "basket.seq", source = "basket_seq")
    BasketMenu basketMenu_To_Entity(BasketMenuDto basketMenuDto);

    // baket
    @Mapping(target = "chatRoom", source = "chatRoom")
    BasketDto basket_To_DTO(Basket basket);
    @Mapping(target = "chatRoom", source = "chatRoom")
    Basket basket_TO_Entity(BasketDto basketDto);

//    // menu
    @Mapping(target = "restaurant_seq", source = "restaurant.seq")
    MenuDto menu_To_DTO(Menu menu);
    List<MenuDto> menu_To_List_DTO(List<Menu> menus);
    @Mapping(target = "restaurant.seq", source = "restaurant_seq")
    Menu menu_To_Entity(MenuDto menuDto);
}
