package com.expedia.offers.dto;
import lombok.Data;

@Data
public class HotelDeeplinks {
    private String hotelInfositeUrl;
    private String hotelSearchResultUrl;
    private String pinnedHotelSearchResultUrl;
    private String decodedHotelInfositeUrl;
    private String decodedHotelSearchResultUrl;
    private String decodedHsrSortByVrUrl;
    private String decodedPinnedHsrSortByRecommendedUrl;
    private String decodedPinnedHsrSortByBestUrl;
    private String decodedPinnedHsrSortByModUrl;
    private String decodedPinnedHsrSortByVrUrl;
    private String similarSearchLink;
}