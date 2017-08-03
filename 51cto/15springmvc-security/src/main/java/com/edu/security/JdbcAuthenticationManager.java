package com.edu.security;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JdbcAuthenticationManager extends JdbcTemplate implements AuthenticationManager {

	public JdbcAuthenticationManager(DataSource dataSource){
		super(dataSource);
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("============自定义authenticate============");
		System.out.println(authentication);
		if(authentication instanceof UsernamePasswordAuthenticationToken){
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
			//cj-comment:可以从UsernamePasswordAuthenticationFilter attemptAuthentication中查看出principal,credentials含义

			String username = token.getPrincipal().toString();
			String password = token.getCredentials().toString();
			
			List<Map<String, Object>> usersInfo = this.queryForList("select * from users where username=? and password=? and enabled=1", username, password);
			if(usersInfo == null || usersInfo.isEmpty()){
				throw new BadCredentialsException("用户名或密码错误");
			}
			
			List<Map<String, Object>> authoritiesList = this.queryForList("select * from authorities where username=?", username);
			if(authoritiesList == null || authoritiesList.isEmpty()){
				throw new NotAnyAuthoritiesException("用户[" + username + "]没有任何权限");
			}
			
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			for(Map<String, Object> authoritie : authoritiesList){
				if(authoritie.get("authority") != null){
					//comment-cj:这里是很容易出错的地方,这里加了前缀"ROLE_",则数据库里权限表不能再加"ROLE_"
					//authorities.add(new SimpleGrantedAuthority("ROLE_" + authoritie.get("authority").toString()));
					authorities.add(new SimpleGrantedAuthority( authoritie.get("authority").toString()));
				}
			}
			
			return new UsernamePasswordAuthenticationToken(username, password, authorities);
		}
		throw new NotSupportAuthenticationException("不支持当前的Authentication: " + authentication.getClass());
	}

}
