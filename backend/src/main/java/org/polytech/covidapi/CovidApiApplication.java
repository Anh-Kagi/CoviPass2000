package org.polytech.covidapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@Configuration
public class CovidApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CovidApiApplication.class, args);
    }
}
