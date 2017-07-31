package com.edu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");	
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("USER", "ADMIN");	
	}
	/**
	 * AntPathRequestMatcher 
	 * 使用的是Apache Ant的样式路径，有三种通配符的匹配方式
	 * ?  匹配任意单个字符
	 * *  匹配0或者任意数量的字符
	 * ** 匹配0或者更多的目录
	 * 
	 * 做url匹配的时候，不需要加上context path
	 * 
	 */
	protected void configure(HttpSecurity http) throws Exception {
		//
		http.csrf().disable();
		
		//对/home 进行匹配，不管HTTP METHOD是什么
//		http.authorizeRequests().antMatchers("/home").hasRole("USER");
		
		//匹配/home，且http method是POST
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/home").hasRole("USER");
		
		//匹配 /home，且http method是GET
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/home").access("permitAll");
		
		
//		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
		
		http.authorizeRequests().antMatchers("/**/*.html").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.js").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.css").access("permitAll");
		
		http.authorizeRequests().antMatchers("/**").access("authenticated");
		
		http.formLogin();
	}
}
