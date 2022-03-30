package com.rs.springsecurity.services;

import com.rs.springsecurity.web.model.BeerOrderDto;
import com.rs.springsecurity.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * created by rs 3/7/2022.
 */
public interface BeerOrderService {
    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    void pickupOrder(UUID customerId, UUID orderId);

    BeerOrderPagedList listOrders(Pageable pageable);

    BeerOrderDto getOrderById(UUID orderId);

}
