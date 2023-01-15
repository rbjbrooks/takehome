package com.example.takehome;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TakehomeApplication {

	public static void main(String[] args) {
		log.debug("This is a debug message");
		SpringApplication.run(TakehomeApplication.class, args);
	}

	@Bean
	public HttpGraphQlClient getHttpGraphQlClient(@Value("${takehome.webclient.baseurl}") String baseUrl) {
		WebClient webClient = WebClient.create(baseUrl);
		return HttpGraphQlClient.create(webClient);
	}
}
