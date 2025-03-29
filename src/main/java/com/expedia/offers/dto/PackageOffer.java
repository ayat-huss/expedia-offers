package com.expedia.offers.dto;

import lombok.Data;

@Data
public class PackageOffer {
    private OfferDateRange offerDateRange;
    private Destination destination;
    private boolean displayDestinationAlternativeIsAvailable;
    private HotelInfo hotelInfo;
    private HotelPricingInfo hotelPricingInfo;
    private FlightInfo flightInfo;
    private FlightPricingInfo flightPricingInfo;
    private PackageInfo packageInfo;
    private PackagePricingInfo packagePricingInfo;
    private PackageUrls packageUrls;
}

 

