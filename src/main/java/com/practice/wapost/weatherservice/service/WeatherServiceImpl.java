package com.practice.wapost.weatherservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Value("${api.weatherservice.rest.url}")
	private String weatherServiceUrl;

	@Value("${api.weatherservice.rest.appid}")
	private String appId;
	@Override
	public String checkWeather(String zipCode) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherServiceUrl).queryParam("zip", zipCode)
				.queryParam("appid", appId);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> response=null;
		try {
			response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					String.class);
			System.out.println("Response:" + response.getBody());
			return response.getBody();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return zipCode;
	}

}
