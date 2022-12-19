package org.polytech.covidapi.configuration;

import org.polytech.covidapi.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuthenticationConfigurer {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthService auth) throws Exception {
        http.authorizeHttpRequests()
                .mvcMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and().httpBasic(Customizer.withDefaults())
                .cors().disable()
                .csrf().disable()  // TODO: enable
                .userDetailsService(auth)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .formLogin().loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout");
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
