package com.example.takehome.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.takehome.api.converter.ContinentConverter;
import com.example.takehome.api.model.ContinentDTO;
import com.example.takehome.service.ContinentsService;

import reactor.core.publisher.Flux;

@RestController()
public class ContinentsController {

    private final ContinentsService continentsService;

    private final ContinentConverter continentConverter;

    /**
     * @param continentsService
     * @param continentConverter
     */
    public ContinentsController(ContinentsService continentsService, ContinentConverter continentConverter) {
        this.continentsService = continentsService;
        this.continentConverter = continentConverter;
    }

    @GetMapping(value = "/continent", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Flux<ContinentDTO> getContinents(@RequestParam("countryCodes") List<String> countryCodes){
        if(countryCodes == null || countryCodes.isEmpty()) {
            return Flux.empty();
        }
        
        return continentsService
            .getContinentsByCountries(countryCodes)
            .flatMap(continentConverter::convertContinent);
    }
}
