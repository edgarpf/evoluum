package com.evoluum.localidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class LocalidadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalidadeApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   RestTemplate restTemplate = builder.build();	   
	   restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());	
	   return restTemplate;
	}
}
