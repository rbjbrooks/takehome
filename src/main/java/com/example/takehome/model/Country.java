package com.example.takehome.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Country {
    @EqualsAndHashCode.Include
    private String code;
    private String name;
    private Continent continent;

    @Override
    public String toString() {
        return code;
    }

    public static Country from(String countryCode){
        Country country = new Country();
        country.setCode(countryCode);
        return country;
    }
}
