package com.rs.springsecurity.web.controllers;

import com.rs.springsecurity.repositories.BeerInventoryRepository;
import com.rs.springsecurity.repositories.BeerRepository;
import com.rs.springsecurity.repositories.CustomerRepository;
import com.rs.springsecurity.services.BeerOrderService;
import com.rs.springsecurity.services.BeerService;
import com.rs.springsecurity.services.BreweryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * created by rs 3/11/2022.
 */
@WebMvcTest
public class IndexControllerIT extends BaseIT{
    @MockBean
    BeerRepository beerRepository;

    @MockBean
    BeerInventoryRepository beerInventoryRepository;

    @MockBean
    BreweryService breweryService;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    BeerService beerService;

    @MockBean
    BeerOrderService beerOrderService;

    @Test
    void testGetIndexSlash() throws Exception{
        mockMvc.perform(get("/" ))
                .andExpect(status().isOk());
    }
}
