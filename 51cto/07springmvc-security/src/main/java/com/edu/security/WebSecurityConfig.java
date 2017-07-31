package com.edu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("zhangsan").password("123").accountLocked(true).roles("USER");	
		auth.inMemoryAuthentication().withUser("lisi").password("123").accountExpired(true).roles("USER");	
		auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");	
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("USER", "ADMIN");	
	}
	
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
		
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
		
		/**
		 * logout默认的url是 /logout
		 * 如果csrf启用，则请求方式是POST，否则请求方式是GET、POST、PUT、DELETE
		 */
		http.logout()
//		.logoutUrl("/sys/doLogout")  //只支持定制退出url
		.logoutRequestMatcher(new AntPathRequestMatcher("/sys/doLogout", "GET")) //支持定制退出url以及httpmethod
		.addLogoutHandler((req, resp, auth) ->{System.out.println("=======1=====");})
		.addLogoutHandler((req, resp, auth) ->{System.out.println("=======2=====");})
		.addLogoutHandler((req, resp, auth) ->{System.out.println("=======3=====");})
//		.logoutSuccessHandler((req, resp, auth) -> {
//			System.out.println("==============4");
//			resp.sendRedirect("/public/html/logout.html");
//		})
		.logoutSuccessUrl("/public/html/logout2.html")
		.clearAuthentication(true)
				//.deleteCookies()
		.invalidateHttpSession(true);
	}
}





