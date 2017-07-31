package com.edu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.edu.security.vo.LoginResp;
import com.edu.security.vo.LogoutResp;
import com.google.gson.Gson;

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
		
		http.authorizeRequests().antMatchers("/sys/ajaxLogin").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.html").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.js").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.css").access("permitAll");
		http.authorizeRequests().antMatchers("/**/*.jpg").access("permitAll");
		
		http.authorizeRequests().anyRequest().authenticated();
		
		http.formLogin()
		.loginPage("/sys/login")
		.loginProcessingUrl("/sys/doLogin")
		.successHandler((req, resp, auth) -> {
			LoginResp lr = new LoginResp(1, "登陆成功");
			String json = new Gson().toJson(lr);
			
			resp.setContentType("application/json");
			resp.getWriter().write(json);
		})
		.failureHandler((req, resp, excp) -> {
			LoginResp lr = new LoginResp();
			lr.setCode(0);
			
			if(excp instanceof BadCredentialsException){
				lr.setMsg("用户名或密码错误");
			} else if(excp instanceof AccountExpiredException){
				lr.setMsg("账户过期");
			} else if(excp instanceof LockedException){
				lr.setMsg("账户已被锁");
			} else {
				lr.setMsg("登陆失败");
			}
			
			String json = new Gson().toJson(lr);
			
			resp.setContentType("application/json");
			resp.getWriter().write(json);
		})
		.permitAll();
		
		/**
		 * logout默认的url是 /logout
		 * 如果csrf启用，则请求方式是POST，否则请求方式是GET、POST、PUT、DELETE
		 */
		http.logout()
//		.logoutUrl("/sys/doLogout")  //只支持定制退出url
		.logoutRequestMatcher(new AntPathRequestMatcher("/sys/doLogout", "GET")) //支持定制退出url以及httpmethod
		.logoutSuccessHandler((req, resp, auth) -> {
			LogoutResp lr = new LogoutResp();
			lr.setCode(1);
			lr.setMsg("退出成功");
			String json = new Gson().toJson(lr);
			
			resp.setContentType("application/json");
			resp.getWriter().write(json);
		})
		.clearAuthentication(true)
		.invalidateHttpSession(true);
	}
}





