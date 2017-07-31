package com.edu.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

	/**
	 * http://127.0.0.1:8000/home
	 * 
	 * http[s]://[host]:[port][request url][?queryString]
	 * 
	 * request url = [context path][servlet path][path info]
	 * 
	 * @param req
	 * @return
	 */
	@GetMapping("/home")
	public String home(HttpServletRequest req){
		System.out.println("==================");
		System.out.println(" req.getRequestURI(): " + req.getRequestURI());
		System.out.println("req.getContextPath(): " + req.getContextPath());
		System.out.println("req.getServletPath(): " + req.getServletPath());
		System.out.println("   req.getPathInfo(): " + req.getPathInfo());
		System.out.println("==================");
		return "spring security home";
	}
	
	@GetMapping("/admin")
	public String admin(){
		return "spring security admin";
	}
}
