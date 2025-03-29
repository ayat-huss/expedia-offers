package com.expedia.offers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.expedia.offers.dto.OfferDTO;
import com.expedia.offers.response.dto.ExpediaErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExpediaOfferService {
	
	
	private final ObjectMapper objectMapper;
	private final RestTemplate restTemplate;

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

	public ExpediaOfferService(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	 

	public ResponseEntity<?> getOffers(String originCity, String destinationCity) {
		try {
			String url = UriComponentsBuilder
					.fromHttpUrl(baseUrl)
					.queryParam("scenario", scenario)
					.queryParam("page", page)
					.queryParam("uid", uid)
					.queryParam("productType", productType)
					.queryParam("clientId", clientId)
					.queryParam("originCity", originCity)
					.queryParam("destinationCity", destinationCity)
					.toUriString();

			log.info("Making API request to base URL: {}", baseUrl);
			 
			log.debug(" originCity={}, destinationCity={}", 
			     originCity, destinationCity);
			 
			String jsonResponse = restTemplate.getForObject(url, String.class);

			 
			JsonNode rootNode = objectMapper.readTree(jsonResponse);
			List<OfferDTO> offers = new ArrayList<>();

			 
			JsonNode packages = rootNode.path("offers").path("Package");
			if (packages.isArray()) {
				for (JsonNode packageNode : packages) {
					OfferDTO offer = new OfferDTO();
					offer.setHotelName(packageNode.path("hotelInfo").path("hotelName").asText());
					offer.setFrom(packageNode.path("offerDateRange").path("formattedTravelStartDate").asText());
					offer.setTo(packageNode.path("offerDateRange").path("formattedTravelEndDate").asText());
					offer.setOfferPrice(
							packageNode.path("packagePricingInfo").path("formattedTotalPriceValue").asText());
					offer.setImage(packageNode.path("hotelInfo").path("hotelImageUrl").asText());
					offer.setTravelEndDate(
							packageNode.path("offerDateRange").path("formattedTravelEndDate").toString());
					offer.setOriginCity(packageNode.path("origin").path("city").asText());
					offer.setDestinationCity(packageNode.path("destination").path("city").asText());

					offers.add(offer);
				}
			}

			return ResponseEntity.ok(offers);
		} catch (HttpClientErrorException e) {
            log.error("Expedia API error: {}", e.getResponseBodyAsString(), e);

			try {
				ExpediaErrorResponse errorResponse = objectMapper.readValue(e.getResponseBodyAsString(),
						ExpediaErrorResponse.class);
				return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
			} catch (Exception ex) {
                log.error("Error parsing error response: {}", ex.getMessage(), ex);

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error parsing error response");
			}
		} catch (Exception e) {
            log.error("Unexpected error occurred while fetching offers: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}

	}

}
