package com.edu.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * JDBC认证 用户必须有至少一个权限，否则不能登录,会抛出BadCredentialsException异常
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/home").hasRole("USER");
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN");
		
		http.authorizeRequests().antMatchers("/**/*.html").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.js").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.css").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.jpg").access("permitAll");
		
		http.authorizeRequests().anyRequest().authenticated();
		
		http.formLogin()
		.loginPage("/sys/login")
		.loginProcessingUrl("/sys/doLogin")
		.failureForwardUrl("/sys/loginFail")
		.defaultSuccessUrl("/public/login/ok.html") //如果直接访问登陆页面，则登陆成功后重定向到这个页面，否则
		.permitAll();

		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/sys/logout", "GET"))
		.logoutSuccessUrl("/public/login/ok.html")
		.clearAuthentication(true)
		.invalidateHttpSession(true)
		.permitAll();
	}
}
