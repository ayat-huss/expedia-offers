package com.expedia.offers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient() {
		return WebClient.builder()

				.defaultHeader(HttpHeaders.USER_AGENT,
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36 Edg/133.0.0.0")
				.defaultHeader(HttpHeaders.ACCEPT,
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8")
				.defaultHeader(HttpHeaders.REFERER, "https://www.expedia.com/")
				.defaultHeader(HttpHeaders.ORIGIN, "https://www.expedia.com")
				.defaultHeader(HttpHeaders.CONNECTION, "keep-alive")
				.defaultHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
				.defaultHeader("authority", "offersvc.expedia.com").defaultHeader("Sec-Fetch-Site", "same-origin")
				.defaultHeader("Sec-Fetch-Mode", "navigate").defaultHeader("Sec-Fetch-Dest", "document")
 
				.build();

	}

}
