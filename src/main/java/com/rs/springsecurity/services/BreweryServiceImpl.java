package com.rs.springsecurity.services;

import com.rs.springsecurity.domain.Brewery;
import com.rs.springsecurity.repositories.BreweryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by rs 3/7/2022.
 */
@Slf4j
@Service
@AllArgsConstructor
public class BreweryServiceImpl implements BreweryService {

    private final BreweryRepository breweryRepository;

    @Override
    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }
}
