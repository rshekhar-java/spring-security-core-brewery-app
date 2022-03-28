package com.rs.springsecurity.repositories;

import com.rs.springsecurity.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * created by rs 3/6/2022.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllByCustomerNameLike(String customerName);

    Optional<Customer> findAllByCustomerName(String customerName);
}
