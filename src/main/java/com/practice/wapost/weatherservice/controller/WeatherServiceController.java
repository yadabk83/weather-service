package com.practice.wapost.weatherservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.wapost.weatherservice.model.WeatherServiceRequest;
import com.practice.wapost.weatherservice.service.WeatherService;

@RestController
public class WeatherServiceController {
	
	@Autowired
	WeatherService weatherService;

	@PostMapping("/find-weather")
	public String findWeather(  @RequestBody WeatherServiceRequest request, HttpServletRequest servletRequest) {	
			String response = weatherService.checkWeather(request.getZipCode());
		return response;

	}
}
