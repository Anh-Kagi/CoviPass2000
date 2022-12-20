package org.polytech.covidapi.configuration;

import org.polytech.covidapi.filter.EtagFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class EtagConfigurer {
	@Bean
	public ShallowEtagHeaderFilter etagFilter() {
		return new EtagFilter();
	}
}
