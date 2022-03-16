package com.rs.springsecurity.web.mappers;

import com.rs.springsecurity.domain.Beer;
import com.rs.springsecurity.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * created by rs 3/7/2022.
 */
@Mapper(uses = DateMapper.class,componentModel = "spring")
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
