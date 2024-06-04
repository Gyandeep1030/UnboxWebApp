package com.example.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
public class Hrcontroller {
	
	@GetMapping("/payrollwrite")
	public String getPayRollWritePage() {
		return "pages/payRollWritePage";
	}

}
