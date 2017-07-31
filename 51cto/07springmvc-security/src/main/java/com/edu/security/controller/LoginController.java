package com.edu.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/sys/login")
	public String login(){
		return "/jsp/login";
	}
	
	@GetMapping("/sys/logout")
	public String logout(){
		return "/jsp/logout";
	}
	
	@PostMapping("/sys/loginFail")
	public String fail(HttpServletRequest req){
		AuthenticationException exp = (AuthenticationException)req.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		if(exp instanceof BadCredentialsException){
			req.setAttribute("error_msg", "用户名或密码错误");
		} else if(exp instanceof AccountExpiredException){
			req.setAttribute("error_msg", "账户过期");
		} else if(exp instanceof LockedException){
			req.setAttribute("error_msg", "账户已被锁");
		}
		return "/jsp/login";
	}
}
