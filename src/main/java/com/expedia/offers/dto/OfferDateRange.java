package com.expedia.offers.dto;

import lombok.Data;

@Data
public class OfferDateRange  {
    private int[] travelStartDate;
    private int[] travelEndDate;
    private String formattedTravelStartDate;
    private String formattedTravelEndDate;
    private int lengthOfStay;
}