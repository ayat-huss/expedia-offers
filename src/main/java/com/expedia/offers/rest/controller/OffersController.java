package com.expedia.offers.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expedia.offers.response.dto.ExpediaOfferResponse;
import com.expedia.offers.service.ExpediaOfferService;
import com.expedia.offers.service.OffersService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
@Slf4j
@RestController
@RequestMapping("/api/offers")
@CrossOrigin(origins = "*")  
public class OffersController {

	private final OffersService offerService;
	private final ExpediaOfferService expediaOfferService;

	public OffersController(OffersService offerService, ExpediaOfferService expediaOfferService) {

		this.offerService = offerService;
		this.expediaOfferService = expediaOfferService;
	}

	@GetMapping
	public ResponseEntity<?> getOffers(
			@RequestParam(required = false) String originCity,
			@RequestParam(required = false) String destinationCity) {
		
		
		log.info("Received request for offers from origin: {} to destination: {}", originCity, destinationCity);
		
		ResponseEntity<?> response = expediaOfferService.getOffers(originCity, destinationCity);
		
		 

		return response;
	}

	// this API just to test call Expedia API using WebClient
	@GetMapping("/search")
	public Mono<List<ExpediaOfferResponse>> getPackageOffers(
		    @RequestParam(required = false) String originCity, 
			@RequestParam(required = false) String destinationCity) {
		log.info("Received request for offers from origin: {} to destination: {}", originCity, destinationCity);

		Mono<List<ExpediaOfferResponse>> offers = offerService.getOffers(originCity, destinationCity);
		 
		return offers;
	}

}
