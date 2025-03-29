package com.expedia.offers.dto;

import lombok.Data;

@Data
public class PackageUrls {
    private String packageSearchUrl;
    private String decodedPackageSearchUrl;
    private String similarSearchLink;
    private FlightDeeplinks flightDeeplinks;
    private HotelDeeplinks hotelDeeplinks;
}
