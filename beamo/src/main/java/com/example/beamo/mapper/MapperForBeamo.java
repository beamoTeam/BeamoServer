package com.example.beamo.mapper;

import com.example.beamo.dto.menu.BasketMenuDto;
import com.example.beamo.repository.baskets.Basket;
import com.example.beamo.repository.baskets.menu.BasketMenu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperForBeamo {
    MapperForBeamo INSTANCE = Mappers.getMapper(MapperForBeamo.class);

    @Mapping(target = "basket_seq", source = "basket.seq")
    BasketMenuDto basketMenu_To_DTO(BasketMenu basketMenu);
    @Mapping(target = "basket.seq", source = "basket_seq")
    BasketMenu basketMenu_To_Entity(BasketMenuDto basketMenuDto);
}
