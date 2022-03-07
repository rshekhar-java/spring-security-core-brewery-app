package com.rs.springsecurity.repositories;

import com.rs.springsecurity.domain.BeerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * created by rs 3/6/2022.
 */
public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {
}
