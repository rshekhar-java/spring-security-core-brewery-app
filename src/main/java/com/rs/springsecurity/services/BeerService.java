package com.rs.springsecurity.services;

import com.rs.springsecurity.web.model.BeerDto;
import com.rs.springsecurity.web.model.BeerPagedList;
import com.rs.springsecurity.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * created by rs 3/7/2022.
 */
public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto findBeerById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteById(UUID beerId);

    BeerDto findBeerByUpc(String upc);
}
