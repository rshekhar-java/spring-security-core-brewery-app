package com.rs.springsecurity.web.mappers;

import com.rs.springsecurity.domain.BeerOrder;
import com.rs.springsecurity.web.model.BeerOrderDto;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

/**
 * created by rs 3/7/2022.
 */
@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class},componentModel = "spring")
public interface BeerOrderMapper {
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
