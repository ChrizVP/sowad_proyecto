package com.proyecto.app.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class AuthenticationController {

	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	
	@GetMapping("/register")
	public String register(Model model) {
		return "register";
	}

	@RequestMapping("/")
	public String inicio(Model model) {
		return "redirect:/index";
	}

	
	
	
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
	
	
}
