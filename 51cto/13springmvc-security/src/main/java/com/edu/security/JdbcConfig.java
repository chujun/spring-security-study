package com.edu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

	@Bean
	public DataSource createDataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setUrl("jdbc:mysql://127.0.0.1:3306/spring-security");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		return ds;
	}
}
