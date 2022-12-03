package org.polytech.covidapi;

import org.polytech.covidapi.service.AuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableWebSecurity
public class CovidApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CovidApiApplication.class, args);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthService auth) throws Exception {
        http.authorizeHttpRequests()
                .mvcMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and().httpBasic(Customizer.withDefaults())
                .cors().disable()
                .csrf().disable()
                .userDetailsService(auth)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
