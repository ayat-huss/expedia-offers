package com.expedia.offers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Offers {
    @JsonProperty("Package")
    private List<PackageOffer> packageOffers;
}