package com.example.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sale")
public class SaleController {

	@GetMapping("/customerManagement")
	public String getcustomerManagementPage() {
		return "pages/customerManagement";
	}
}
