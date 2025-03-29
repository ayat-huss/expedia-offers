package com.expedia.offers.dto;

import lombok.Data;

@Data
public class ErrorInfo {

	private int errorCode;
	private String errorMessage;
}
