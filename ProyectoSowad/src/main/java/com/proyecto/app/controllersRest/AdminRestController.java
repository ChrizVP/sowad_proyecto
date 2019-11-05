package com.proyecto.app.controllersRest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.app.models.User;
import com.proyecto.app.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping({"/admin/"})
public class AdminRestController {


	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping ("/admin/add")
	public String addUserByAdmin(@RequestBody User user) {
		String pwd = user.getPassword();
		String encryptPwd = PasswordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		userRepository.save(user);
		return "user added successfully...";
	}	
	
	
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/all")
	public String securedHello() {
		return "holaaaa";
	}
	
	@GetMapping("/se")
	public String login(){
		return "authentidated successfully";
	}
	
	
	
}
