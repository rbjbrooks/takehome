package com.example.takehome.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Continent {
    @EqualsAndHashCode.Include
    private String name;
    private List<Country> countries;
    private List<Country> otherCountries;
}
