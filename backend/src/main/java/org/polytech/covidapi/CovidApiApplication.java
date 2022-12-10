package org.polytech.covidapi;

import org.polytech.covidapi.filter.EtagFilter;
import org.polytech.covidapi.service.AuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableWebSecurity
@Configuration
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
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());  // for test purpose only

        // default password hashing algorithm is bcrypt (spring's current default)
        // but can validate (and re-hash) password using older hashing algorithm
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    @Bean
    public ShallowEtagHeaderFilter etagFilter() {
        return new EtagFilter();
    }

    @Bean
    @Profile("dev")
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("data-init.json")});
        return factory;
    }
}
