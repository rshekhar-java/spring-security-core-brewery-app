package com.rs.springsecurity.web.mappers;

import com.rs.springsecurity.domain.Beer;
import com.rs.springsecurity.domain.BeerInventory;
import com.rs.springsecurity.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * created by rs 3/7/2022.
 */
public abstract class BeerMapperDecorator implements BeerMapper{
    private BeerMapper beerMapper;
    @Autowired
    @Qualifier("delegate")
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {

        BeerDto dto = beerMapper.beerToBeerDto(beer);

        if(beer.getBeerInventory() != null && beer.getBeerInventory().size() > 0) {
            dto.setQuantityOnHand(beer.getBeerInventory()
                    .stream().map(BeerInventory::getQuantityOnHand)
                    .reduce(0, Integer::sum));

        }

        return dto;
    }
}
