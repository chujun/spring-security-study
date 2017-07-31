package com.edu.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {

	@Bean
	public DataSource createDataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername("root");
		ds.setPassword("admin123");
		ds.setUrl("jdbc:mysql://127.0.0.1:3306/user_security");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		return ds;
	}
}
