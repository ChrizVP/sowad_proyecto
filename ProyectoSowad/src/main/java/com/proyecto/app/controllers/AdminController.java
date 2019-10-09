package com.proyecto.app.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



import com.proyecto.app.models.User;
import com.proyecto.app.repository.UserRepository;


@Controller
public class AdminController {


	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	
	@RequestMapping("/")
	public String inicio(Model model) {
		return "redirect:/index";
	}

	
	
	
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping ("/admin/add")
	public String addUserByAdmin(@RequestBody User user) {
		String pwd = user.getPassword();
		String encryptPwd = PasswordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		userRepository.save(user);
		return "user added successfully...";
	}	
	
	
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/admin/all")
	public String securedHello() {
		return "redirect:index";
	}
	
}
