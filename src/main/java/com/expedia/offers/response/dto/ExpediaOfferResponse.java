package com.expedia.offers.response.dto;

import java.util.Map;

import com.expedia.offers.dto.AbInfo;
import com.expedia.offers.dto.DebugInfo;
import com.expedia.offers.dto.OfferInfo;
import com.expedia.offers.dto.Offers;
import com.expedia.offers.dto.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpediaOfferResponse {
    private OfferInfo offerInfo;
    private UserInfo userInfo;
    private DebugInfo debugInfo;
    private AbInfo abInfo;
    private Map<String, Object> pricingSummaries;
    private Offers offers;
    
    
    
    
  

    
}