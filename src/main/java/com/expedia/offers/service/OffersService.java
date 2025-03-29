package com.expedia.offers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.expedia.offers.response.dto.ExpediaOfferResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OffersService {

	@Autowired
	private   WebClient webClient;

	@Value("${expedia.base-url}")
	private String baseUrl;

	@Value("${expedia.scenario}")
	private String scenario;

	@Value("${expedia.page}")
	private String page;

	@Value("${expedia.uid}")
	private String uid;

	@Value("${expedia.productType}")
	private String productType;

	@Value("${expedia.clientId}")
	private String clientId;

	public Mono<List<ExpediaOfferResponse>> getOffers(String originCity , String destinationCity) {
       
		  String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
				  .queryParam("scenario", scenario)
                  .queryParam("page", page)
                  .queryParam("uid",  uid)
                  .queryParam("productType", productType)
                  .queryParam("clientId", clientId)
                  .queryParam("originCity", originCity)
                  .queryParam("destinationCity", destinationCity)
                  .toUriString();
		  log.info("Making API request to base URL: {}", baseUrl);
		 
		log.debug(" originCity={}, destinationCity={}", 
		     originCity, destinationCity);
		  
		return webClient.get()
                .uri(url)              
                .retrieve()
                .onStatus(HttpStatus::isError, this::handleError) 
                .bodyToFlux(ExpediaOfferResponse.class)
                .collectList()
                .doOnTerminate(() -> log.info("API request completed"));
    }

	private Mono<? extends Throwable> handleError(ClientResponse response) {
		 
		HttpStatus status = response.statusCode();
		if (status == HttpStatus.NOT_FOUND) {
			log.error("API not found - 404 status");
			return Mono.error(new RuntimeException("API not found"));
		} else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
			return Mono.error(new RuntimeException("Internal server error"));
		}
		log.error("Unexpected error with status: {}", status);
		return Mono.error(new RuntimeException("Unexpected error with status: " + status));
	} 

}