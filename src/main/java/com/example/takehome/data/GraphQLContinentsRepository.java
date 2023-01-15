package com.example.takehome.data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Repository;

import com.example.takehome.exception.ServiceDownException;
import com.example.takehome.model.Continent;
import com.example.takehome.model.Country;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Repository
@Slf4j
public class GraphQLContinentsRepository implements ContinentsRepository{

    private final HttpGraphQlClient graphQlClient;

    private static final String COUNTRY_QUERY = 
    "  {" +
    "    countries(filter: {code: {in: %s}}) {" +
    "      code," +
    "      name," +
    "      continent {" +
    "        name," +
    "        countries {" +
    "          code" +
    "        }" +
    "      }" +
    "    }" +
    "  }";

    /**
     * @param graphQlClient
     */
    public GraphQLContinentsRepository(HttpGraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }



    @Override
    public Flux<Continent> getContinentsByCountries(List<String> countryCodes) {
        String query = String.format(COUNTRY_QUERY, getArrayString(countryCodes));
        log.info(String.format("getContinentsByCountries() - query: %s", query));
        return graphQlClient.document(query)
            .retrieve("countries")
            .toEntityList(Country.class)
            .flatMapIterable(countryList -> {
               return countryList.stream().map(Country::getContinent).collect(Collectors.toList());
            }).distinct()
            .onErrorMap(Exception.class, exception -> {
                log.warn("getContinentsByCountries() - error getting continents from service.", exception);
                return new ServiceDownException("Service is currently unavailable.");
            });

    }

    private String getArrayString(List<String> countries) {
        return countries.stream().map(country -> String.format("\"%s\"", country)).collect(Collectors.joining(",", "[", "]"));
    }
    
}
