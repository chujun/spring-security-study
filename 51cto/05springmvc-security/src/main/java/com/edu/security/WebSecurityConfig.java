package com.edu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

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
	 * 重要：
	 * 1：做url匹配的时候，不需要加上context path
	 * 2：顺序
	 * 
	 */
	protected void configure(HttpSecurity http) throws Exception {
		//https://my.oschina.net/Cubicluo/blog/859957
		http.csrf().disable();
		
		//对/home 进行匹配，不管HTTP METHOD是什么
//		http.authorizeRequests().antMatchers("/home").hasRole("USER");
		
//		http.authorizeRequests().antMatchers(HttpMethod.GET).access("permitAll");
		
		http.authorizeRequests().antMatchers("/user/list").access("authenticated");
		http.authorizeRequests().antMatchers("/user/**").access("permitAll");

		
		//匹配/home，且http method是POST
//		http.authorizeRequests().antMatchers(HttpMethod.POST, "/home").hasRole("USER");
		
		//匹配 /home，且http method是GET
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/home").access("permitAll");
		
//		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN");
//		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
		
//		http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/home").access("permitAll");
//		http.authorizeRequests().mvcMatchers(HttpMethod.POST, "/home").hasRole("USER");
		
//		http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/home").servletPath("/v2").access("permitAll");


		//自定义Request Matcher
		http.authorizeRequests().requestMatchers(new RequestMatcher(){
			public boolean matches(HttpServletRequest request) {
				return "1".equals(request.getParameter("type"));
			}
		}).access("permitAll");
		
		
//		http.authorizeRequests().antMatchers("/user/list.do").access("permitAll");
		
		http.authorizeRequests().antMatchers("/**/*.html").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.js").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.css").access("permitAll");
		
		/**
		 * 匹配所有的几种方式
		 */
		//http.authorizeRequests().antMatchers("/**").access("authenticated");
		//http.authorizeRequests().anyRequest().access("authenticated");
		http.authorizeRequests().requestMatchers(AnyRequestMatcher.INSTANCE).access("authenticated");
		
		http.formLogin();
	}
}
