package com.practice.wapost.weatherservice.model;

import javax.validation.constraints.NotNull;

public class WeatherServiceRequest {
	@NotNull
	private String zipCode;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "WeatherServiceRequest [zipCode=" + zipCode + "]";
	}

}
