package com.edu.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping("/create")
	public String create(){
		return "user create";
	}
	
	@PostMapping("/edit")
	public String edit(){
		return "user edit";
	}
	
	@PostMapping("/delete")
	public String delete(){
		return "user delete";
	}
	
	@GetMapping("/show")
	public String show(){
		return "user show";
	}
	
	@GetMapping("/list")
	public String list(){
		return "user list";
	}
}
