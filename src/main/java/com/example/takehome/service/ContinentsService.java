package com.example.takehome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.takehome.data.ContinentsRepository;
import com.example.takehome.model.Continent;
import com.example.takehome.model.Country;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ContinentsService {
    
    private final ContinentsRepository continentsRepository;

    /**
     * @param continentsRepository
     */
    public ContinentsService(ContinentsRepository continentsRepository) {
        this.continentsRepository = continentsRepository;
    }

    public Flux<Continent> getContinentsByCountries(List<String> countryCodes) {
        return continentsRepository.getContinentsByCountries(countryCodes)
            .flatMap(continent -> {
                //swap countries over to other list
                continent.setOtherCountries(continent.getCountries());
                continent.setCountries(new ArrayList<Country>());

                //now for each country code, check if it is in 'otherCountries', remove it from there and add to 'countries'
                countryCodes.stream().forEach(countryCode -> {
                    Country country = Country.from(countryCode);
                    if(continent.getOtherCountries().remove(country)) {
                        continent.getCountries().add(country);
                    }
                });
                return Mono.just(continent);
            });
    }
}
