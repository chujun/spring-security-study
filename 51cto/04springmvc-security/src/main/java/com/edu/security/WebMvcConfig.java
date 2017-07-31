package com.edu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**/*.html").addResourceLocations("/public");
		registry.addResourceHandler("/**/*.css").addResourceLocations("/public");
		registry.addResourceHandler("/**/*.js").addResourceLocations("/public");
	}
}
