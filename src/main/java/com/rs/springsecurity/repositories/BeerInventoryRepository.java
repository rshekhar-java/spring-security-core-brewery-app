package com.rs.springsecurity.repositories;

import com.rs.springsecurity.domain.Beer;
import com.rs.springsecurity.domain.BeerInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * created by rs 3/6/2022.
 */
public interface BeerInventoryRepository extends JpaRepository<BeerInventory, UUID> {
    List<BeerInventory> findAllByBeer(Beer beer);
}
