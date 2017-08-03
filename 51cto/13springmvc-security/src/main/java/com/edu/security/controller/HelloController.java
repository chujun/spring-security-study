package com.edu.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	/**
	 * index:authentication:class org.springframework.security.authentication.UsernamePasswordAuthenticationToken
	 org.springframework.security.authentication.UsernamePasswordAuthenticationToken@b560cd00: Principal: org.springframework.security.core.userdetails.User@b1384a4b: Username: 18255170291; Password: [PROTECTED]; Enabled: true; AccountNonExpired: true; credentialsNonExpired: true; AccountNonLocked: true; Granted Authorities: ROLE_GUEST,ROLE_USER; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@fffde5d4: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: node0103hditwwmycgnj9nbikn4i4r0; Granted Authorities: ROLE_GUEST, ROLE_USER
	 * @return
     */
	@GetMapping("/index")
	public String index() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("index:authentication:"+authentication.getClass());
		System.out.println(authentication);
		return "spring security index";
	}
	
	@GetMapping("/home")
	public String home() {
		return "spring security get home";
	}
	
	@PostMapping("/home")
	public String postHome(){
		return "spring security post home";
	}
	
	@GetMapping("/admin")
	public String admin(){
		return "spring security admin";
	}
}
