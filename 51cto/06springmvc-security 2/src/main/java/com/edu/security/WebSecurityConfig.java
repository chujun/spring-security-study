package com.edu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("zhangsan").password("123").accountLocked(true).roles("USER");	
		auth.inMemoryAuthentication().withUser("lisi").password("123").accountExpired(true).roles("USER");	
		auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");	
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("USER", "ADMIN");	
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/sys/login").access("permitAll");
		http.authorizeRequests().antMatchers("/sys/loginFail").access("permitAll");
		
		http.authorizeRequests().antMatchers("/**/*.html").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.js").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.css").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.jpg").access("permitAll");
		
		http.authorizeRequests().anyRequest().authenticated();
		
		http.formLogin()
		.loginPage("/sys/login")
		.loginProcessingUrl("/doLogin")
		.failureForwardUrl("/sys/loginFail")  //使用Forward
//		.failureUrl("/public/login/fail.html")  //使用的是重定向
		.defaultSuccessUrl("/public/login/ok.html") //如果直接访问登陆页面，则登陆成功后重定向到这个页面，否则跳转到之前访问的页面
//      .defaultSuccessUrl("/public/login/ok.html", true) //登陆成功后，直接重定向到这个页面
				//chujun-cj:forward可以拿到错误信息,重定向的缺点:无法获取失败原因信息
//		.successHandler(new AuthenticationSuccessHandler(){
//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//					Authentication authentication) throws IOException, ServletException {
//				System.out.println("========登陆成功=======" + authentication.getName());
//				response.sendRedirect("/public/login/ok.html");
//			}
//		})
//		.failureHandler(new AuthenticationFailureHandler(){
//			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//					AuthenticationException exception) throws IOException, ServletException {
//				System.out.println("=======登陆失败=======" + exception.getMessage());
//				response.sendRedirect("/public/login/fail.html");
//			}
//		})
		.permitAll();
	}
}





