package com.expedia.offers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferErrorInfo {

	private int errorCode;
	private String errorMessage;

	 
}
