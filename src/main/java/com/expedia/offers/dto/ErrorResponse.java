package com.expedia.offers.dto;

import lombok.Data;

@Data
public class ErrorResponse {

	private int errorCode;
	private String errorMessage;
}
