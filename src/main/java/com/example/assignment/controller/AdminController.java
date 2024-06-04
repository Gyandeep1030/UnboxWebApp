package com.example.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.assignment.model.Users;
import com.example.assignment.service.UsersService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/userRegistrationPage")
	public String getUserRegistrationPage() {
		
		return "pages/userRegistrationPage";
	}
	
	@PostMapping("/saveuser")
	public String saveUser(@ModelAttribute Users users) {
		
		usersService.saveUser(users);
		
		return "redirect:/admin/allUsers";
	}
	
	@GetMapping("/allUsers")
	public String getAllUserPage(Model model) {
		model.addAttribute("users",usersService.getAllUsers());
		return "pages/AllUsers";
	}

}
