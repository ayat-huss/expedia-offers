package com.expedia.offers.dto;

import lombok.Data;

@Data
public class PackagePricingInfo {
    private String currencyCode;
    private double perPsgrPackagePrice;
    private double totalPackagePrice;
    private double perPsgrSavings;
    private double totalSavings;
    private int percentSavings;
    private int savingsPctOfHotel;
    private int savingsPctOfFlight;
    private String formattedPerPassengerPackagePrice;
    private String formattedTotalPriceValue;
}