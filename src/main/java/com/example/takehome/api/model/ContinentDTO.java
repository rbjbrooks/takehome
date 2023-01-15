package com.example.takehome.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContinentDTO {
    private List<String> countries;
    private String name;
    private List<String> otherCountries;
}
