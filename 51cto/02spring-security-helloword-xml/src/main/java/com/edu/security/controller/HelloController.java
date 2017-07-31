package com.edu.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8000/public/html/list.html
 * http://localhost:8000/
 * http://localhost:8000/home
 * http://localhost:8000/admin
 *
 * http://localhost:8000/public/html/notExist.html
 */
@RestController
public class HelloController {

	@GetMapping("/home")
	public String home(){
		return "spring security home";
	}
	
	@GetMapping("/admin")
	public String admin(){
		return "spring security admin";
	}
}
