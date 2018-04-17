package com.practice.wapost.weatherservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages= {"com.practice.wapost.weatherservice"})
@ConfigurationProperties(prefix="api.weatherservice.rest")
public class SpringConfig {
	
	@Bean(name="restTemplate")
	public RestTemplate restOperations() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

}
