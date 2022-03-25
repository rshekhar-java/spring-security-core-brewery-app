package com.rs.springsecurity.web.controllers.api;

import com.rs.springsecurity.domain.Beer;
import com.rs.springsecurity.repositories.BeerOrderRepository;
import com.rs.springsecurity.repositories.BeerRepository;
import com.rs.springsecurity.web.controllers.BaseIT;
import com.rs.springsecurity.web.model.BeerStyleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * created by rs 3/12/2022.
 */

@SpringBootTest
public class BeerRestControlllerTest extends BaseIT {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerOrderRepository beerOrderRepository;

    @DisplayName("Delete Tests")
    @Nested
    class DeleteTests {

        public Beer beerToDelete() {
            Random rand = new Random();

            return beerRepository.saveAndFlush(Beer.builder()
                    .beerName("Delete Me Beer")
                    .beerStyle(BeerStyleEnum.IPA)
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(String.valueOf(rand.nextInt(99999999)))
                    .build());
        }

        @Test
        void deleteBeerHttpBasic() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId())
                            .with(httpBasic("spring", "spring")))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        void deleteBeerHttpBasicUserRole() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId())
                            .with(httpBasic("user", "password")))
                    .andExpect(status().isForbidden());
        }

        @Test
        void deleteBeerHttpBasicCustomerRole() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId())
                            .with(httpBasic("scott", "tiger")))
                    .andExpect(status().isForbidden());
        }


        @Test
        void deleteBeerNoAuth() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/" + beerToDelete().getId()))
                    .andExpect(status().isUnauthorized());
        }

    }

        @Test
        void deleteBeerUrl() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/97df0c39-90c4-4ae0-b663-453e8e19c311")
                            .param("apiKey", "spring").param("apiSecret", "spring"))
                    .andExpect(status().isOk());
        }

        @Test
        void deleteBeerBadCredsUrl() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/97df0c39-90c4-4ae0-b663-453e8e19c311")
                            .param("apiKey", "spring").header("apiSecret", "springXXXX"))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void deleteBeerBadCreds() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/97df0c39-90c4-4ae0-b663-453e8e19c311")
                            .header("Api-Key", "spring").header("Api-Secret", "springXXXX"))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void deleteBeer() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/97df0c39-90c4-4ae0-b663-453e8e19c311")
                            .header("Api-Key", "spring").header("Api-Secret", "spring"))
                    .andExpect(status().isOk());
        }

        @Test
        void findBeers() throws Exception {
            mockMvc.perform(get("/api/v1/beer/"))
                    .andExpect(status().isOk());
        }

        @Test
        void findBeerById() throws Exception {
            Beer beer = beerRepository.findAll().get(0);

            mockMvc.perform(get("/api/v1/beer/" + beer.getId()))
                    .andExpect(status().isOk());
        }

        @Test
        void findBeerByUpc() throws Exception {
            mockMvc.perform(get("/api/v1/beerUpc/0631234200036"))
                    .andExpect(status().isOk());
        }

        @Test
        void findBeerFormADMIN() throws Exception {
            mockMvc.perform(get("/beers").param("beerName", "")
                            .with(httpBasic("spring", "spring")))
                    .andExpect(status().isOk());
        }
}
