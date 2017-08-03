package com.edu.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class SecurityConfiguration {

	@Bean
	public AuthenticationManager authenticationManager(DataSource dataSource) {
		return new JdbcAuthenticationManager(dataSource);
	}
}
