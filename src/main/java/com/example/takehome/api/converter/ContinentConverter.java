package com.example.takehome.api.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.takehome.api.model.ContinentDTO;
import com.example.takehome.model.Continent;

import reactor.core.publisher.Mono;

@Component
public class ContinentConverter {
    
    public Mono<ContinentDTO> convertContinent(Continent continent) {
        if(continent != null) {
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(continent.getName());
            continentDTO.setCountries(continent.getCountries().stream().map(Object::toString).collect(Collectors.toList()));
            continentDTO.setOtherCountries(continent.getOtherCountries().stream().map(Object::toString).collect(Collectors.toList()));
            return Mono.just(continentDTO);
        }
        return Mono.empty();
    }
}
