package com.rs.springsecurity.web.mappers;

import com.rs.springsecurity.domain.BeerOrder;
import com.rs.springsecurity.web.model.BeerOrderDto;
import org.mapstruct.Mapper;

/**
 * created by rs 3/7/2022.
 */
@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
