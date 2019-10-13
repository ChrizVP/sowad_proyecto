package com.proyecto.app.controllers;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.proyecto.app.models.Cliente;
import com.proyecto.app.repository.PersonalizadoClienteRepository;
import com.proyecto.app.service.ClienteService;

@RestController
@RequestMapping("/demo")
public class pruebaController {
	
	@Autowired
	private ClienteService ClienteService;
	
	
	
	@Autowired
	private PersonalizadoClienteRepository personalizadoClienteRepository;
	

	@PostMapping("/saveCliente")
	public ResponseEntity<Object> addBook(@RequestBody Cliente cliente) {
		
		
		Cliente response = new Cliente();
		response = personalizadoClienteRepository.findByDni(cliente.getDni());
		//response = ClienteService.get(cliente.getCliente_id());
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;
	}
	
}

