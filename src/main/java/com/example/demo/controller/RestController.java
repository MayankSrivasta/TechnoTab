package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.modal.User;
import com.example.demo.services.UserService;

@org.springframework.web.bind.annotation.RestController
//@RestController
public class RestController {

	@Autowired
	 UserService userService;
	

	
	@GetMapping("/")
	public String hello() {
		return "RestController Class";
	}
	
	
	//localhost:8080/save-user?username=mayanksri94&firstname=mayank&lastname=srivastava&age=25&password=king
	
	@GetMapping("/saveuser")
	public String saveUser(@RequestParam String username,@RequestParam String firstname,@RequestParam String lastname,@RequestParam int age,@RequestParam String password) {
		User user=new User(username,firstname,lastname,password,age);
		userService.saveMyUser(user);
		return "user saved";
	}
	
}
