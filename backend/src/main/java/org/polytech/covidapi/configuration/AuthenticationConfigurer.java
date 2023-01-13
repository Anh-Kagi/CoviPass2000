package org.polytech.covidapi.configuration;

import org.polytech.covidapi.model.Role;
import org.polytech.covidapi.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;


@EnableWebSecurity
@Configuration
public class AuthenticationConfigurer {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthService auth) throws Exception {
		http.authorizeHttpRequests()
				.antMatchers("/private/super/**").hasRole(Role.SUPER_ADMIN.name())
				.antMatchers("/private/simple/**").hasRole(Role.ADMIN.name())
				.antMatchers("/private/medecin/**").hasRole(Role.MEDECIN.name())
				.antMatchers("/private/**").authenticated()
				.anyRequest().permitAll()
				.and().httpBasic(Customizer.withDefaults())
				.csrf().disable()  // TODO: enable
				.userDetailsService(auth)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
				.httpBasic()
				.realmName("CovidAPI");
		return http.build();
	}

	@Bean
	@SuppressWarnings("deprecation")  // ignore NoOpPasswordEncoder deprecation
	public PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());  // for test purpose only

		// default password hashing algorithm is bcrypt (spring's current default)
		// but can validate (and re-hash) password using older hashing algorithm
		return new DelegatingPasswordEncoder("bcrypt", encoders);
	}
}
