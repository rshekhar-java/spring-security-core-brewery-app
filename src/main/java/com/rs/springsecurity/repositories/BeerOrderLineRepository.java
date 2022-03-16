package com.rs.springsecurity.repositories;

import com.rs.springsecurity.domain.BeerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * created by rs 3/6/2022.
 */
@Repository
public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {
}
