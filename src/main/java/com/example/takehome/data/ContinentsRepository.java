package com.example.takehome.data;

import java.util.List;

import com.example.takehome.model.Continent;

import reactor.core.publisher.Flux;

public interface ContinentsRepository {
    Flux<Continent> getContinentsByCountries(List<String> countries);
}
