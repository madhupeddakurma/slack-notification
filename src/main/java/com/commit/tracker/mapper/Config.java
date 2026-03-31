package com.commit.tracker.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class Config {

	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	                // allow H2 console
	                .requestMatchers("/h2-console/**").permitAll()
	                // allow your controller endpoints
	                .requestMatchers("/github/api/saveauthor", "/github/api/savecommit").permitAll()
	                // everything else can be secured or permitted
	                .anyRequest().authenticated()
	            )
	            // disable CSRF for H2 console and your endpoints
	            .csrf(csrf -> csrf
	                .ignoringRequestMatchers("/h2-console/**", "/github/api/saveauthor", "/github/api/savecommit")
	            )
	            // allow frames for H2 console
	            .headers(headers -> headers
	                .frameOptions(frame -> frame.sameOrigin())
	            );

	        return http.build();
	    }
	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http .authorizeHttpRequests(auth -> auth
	 * .requestMatchers("/h2-console/**").permitAll() .anyRequest().permitAll() )
	 * .csrf(csrf -> csrf .ignoringRequestMatchers("/h2-console/**") )
	 * .headers(headers -> headers .frameOptions(frame -> frame.sameOrigin()) );
	 * 
	 * return http.build(); }
	 */
}