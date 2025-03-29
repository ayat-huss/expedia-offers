package com.expedia.offers.dto;

import lombok.Data;

@Data
public class HotelPricingInfo {
    private double hotelPerPsgrBaseRate;
    private double hotelPerPsgrTaxesAndFees;
    private double hotelPerPsgrTotalRate;
    private double hotelTotalBaseRate;
    private double hotelTotalTaxesAndFees;
    private double hotelTotalRate;
    private String currency;
    private String formattedTotalPriceValue;
    private boolean displayAveragePrice;
    private boolean drr;
}