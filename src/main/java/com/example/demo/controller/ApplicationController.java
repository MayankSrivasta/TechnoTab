package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.modal.User;
import com.example.demo.services.UserService;

@Controller
public class ApplicationController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/welcome2")
	public String Welcome2() {

		return "hello world";
	}

	//this was present in homepage.jsp file
	//<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	@RequestMapping("/welcome")
	public String homepageMethod() {
		return "homepage";
	}
	
	
	//this was present in welcomepage.jsp file
	//<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	@RequestMapping("/welcomepage")
	public String welcomePage(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}
	
	@RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "welcomepage";
	}
	
	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute User user,BindingResult bindingResult,HttpServletRequest request) {
		userService.saveMyUser(user);
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}

	@GetMapping("/show-users")
	public String showAllUsers(HttpServletRequest request) {
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage";
	}

	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request) {
		userService.deleteMyUser(id);
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage";
	}
	
	@RequestMapping("/edit-user")
	public String editUser(@RequestParam int id,HttpServletRequest request) {
		request.setAttribute("user", userService.editUser(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "welcomepage";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "welcomepage";
	}
	
	@RequestMapping ("/login-user")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
		if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())!=null) {
			return "homepage";
		}
		else {
			request.setAttribute("error", "Invalid Username or Password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "welcomepage";
			
		}
	}


	
	
}
