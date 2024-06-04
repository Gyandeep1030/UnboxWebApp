package com.example.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

	@GetMapping("/dashboard")
	public String getDashBoard() {
		return "pages/dashboard";
	}
	
	@GetMapping("/payroll")
	public String getpayrollPage() {
		return "pages/PayRoll";
	}
	
	@GetMapping("/billmanagement")
	public String GetBillManagementPage() {
		return "pages/billManagement";
	}
}
