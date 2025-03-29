package com.expedia.offers.response.dto;

import com.expedia.offers.dto.DebugInfo;
import com.expedia.offers.dto.OfferErrorInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
 @AllArgsConstructor
 @NoArgsConstructor
 public class ExpediaErrorResponse {
	 
	 
	 
	private OfferErrorInfo offerErrorInfo;
	private DebugInfo debugInfo;
}
