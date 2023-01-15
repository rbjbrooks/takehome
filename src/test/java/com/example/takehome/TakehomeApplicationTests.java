package com.example.takehome;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.ApiVersion;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.takehome.model.Continent;
import com.example.takehome.service.ContinentsService;

@SpringBootTest
class TakehomeApplicationTests {

    @Autowired
    private ContinentsService continentsService;

    @Test
    void contextLoads() {
        assertEquals(ApiVersion.V3, ApiVersion.LATEST);
    }

    @Test
    void testRetrieveContinentsSuccess() {
        List<Continent> continents = continentsService.getContinentsByCountries(List.of("CA","US")).collectList().block();
        assertNotNull(continents);
        assertEquals(1, continents.size());
        assertEquals("North America", continents.get(0).getName());
    }

    @Test
    void testRetrieveContinentsEmpty() {
        List<Continent> continents = continentsService.getContinentsByCountries(List.of("asdf")).collectList().block();
        assertNotNull(continents);
        assertEquals(0, continents.size());
    }
}
