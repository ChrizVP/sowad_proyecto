package com.proyecto.app.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.app.models.Cliente;
import com.proyecto.app.models.User;
import com.proyecto.app.repository.PersonalizadoClienteRepository;
import com.proyecto.app.repository.UserRepository;


@RequestMapping("/cotizacion")
@RestController
public class CotizacionController {

	
	
	@Autowired
	private PersonalizadoClienteRepository personalizadoClienteRepository;
	
	@Autowired
	private UserRepository UserRepository;
	
	private User user = new User();
	
	@PostMapping("/buscarcliente")
	public ResponseEntity<Object> addBook(@RequestBody Cliente cliente) {
		Cliente response = new Cliente();
		response = personalizadoClienteRepository.findByDni(cliente.getDni());
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping("/buscar/usuario")
	public User user(Principal principal) {
		
		user = UserRepository.findByUsername(principal.getName());
		return user;
			
	}
	
}
