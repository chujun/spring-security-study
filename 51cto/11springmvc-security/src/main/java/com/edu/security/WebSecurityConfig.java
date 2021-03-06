package com.edu.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * JDBC认证 用户必须有至少一个权限，否则不能登录
	 * 启用组之后，用户的所有权限=用户本身的权限+用户所属组的权限
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		JdbcUserDetailsManager judm = auth.jdbcAuthentication().dataSource(dataSource).getUserDetailsService();
		judm.setEnableGroups(false);
		judm.setUsersByUsernameQuery("select phone,userpwd,(case when status in (1,4) then 1 else 0 end) enabled from tb_users where phone=? and is_del=0");
		judm.setAuthoritiesByUsernameQuery("select a.phone,b.role_name from tb_users a,tb_roles b,tb_user_role c where a.is_del=0 and a.phone=? and c.uid=a.uid and c.rid=b.rid");
		//judm.setRolePrefix("ROLE_");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		/**
		 * 方法一
		 * 假设数据库表中的是以AUTH_开头
		 */
//		http.authorizeRequests().antMatchers("/home").access("hasAuthority('AUTH_ADMIN')");
//		http.authorizeRequests().antMatchers("/admin").access("hasAuthority('AUTH_MANAGER')");
		
		/**
		 * 方法二
		 * judm.setRolePrefix("ROLE_");
		 */
		http.authorizeRequests().antMatchers("/home").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin").hasRole("MANAGER");
		
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
