package com.expedia.offers.dto;
import lombok.Data;

@Data
public class FlightInfo {
    private String flightDealCarrier;
    private String flightDealCarrierName;
    private int lengthOfStay;
    private String flightDealCarrierImageUrl;
    private String flightDealCarrierImageUrlSqSvg;
    private String tripType;
}