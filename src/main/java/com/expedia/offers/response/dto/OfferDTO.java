package com.expedia.offers.response.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
 @NoArgsConstructor
public class OfferDTO {
    private String hotelName;
    private String from;
    private String to;
    private String offerPrice;
    private String image;
    private String travelEndDate;
    private String originCity;
    private String destinationCity;
    
    public OfferDTO(String hotelName, String from, String to, String offerPrice, String image, String travelEndDate, String originCity, String destinationCity) {
        this.hotelName = hotelName;
        this.from = from;
        this.to = to;
        this.offerPrice = offerPrice;
        this.image = image;
        this.travelEndDate = travelEndDate;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
    }

	@Override
	public String toString() {
		return "OfferDTO [hotelName=" + hotelName + ", from=" + from + ", to=" + to + ", offerPrice=" + offerPrice
				+ ", image=" + image + ", travelEndDate=" + travelEndDate + ", originCity=" + originCity
				+ ", destinationCity=" + destinationCity + "]";
	}
    
    
}
