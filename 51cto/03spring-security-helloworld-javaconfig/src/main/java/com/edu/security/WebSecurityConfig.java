package com.edu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//可以内存配置多个用户
		auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("USER", "ADMIN");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/home").hasRole("USER");
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN");
		
		http.authorizeRequests().antMatchers("/**/*.html").permitAll();
		http.authorizeRequests().antMatchers("/**/*.css").permitAll();
		http.authorizeRequests().antMatchers("/**/*.js").permitAll();
		//permitAll()的另一种表达方式,表达式方式
		http.authorizeRequests().antMatchers("/**/*.png").access("permitAll");
		//其他所有请求需要登录认证
		http.authorizeRequests().anyRequest().authenticated();
//		http.authorizeRequests().anyRequest().access("authenticated");
		//spring security自动生成登录页面
		http.formLogin();
	}
}
