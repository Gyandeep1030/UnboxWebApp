package com.example.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class IndexController {
	
	@GetMapping("/")
	public String getHomePage() {
		return "homePage";
	}
	
	@GetMapping("/login")
	public String getLandingPage() {
		return "loginPage";
	}
	
	@GetMapping("/info")
	public String getInfoPage() {
		return "InfoPage";
	}
	
}
