package com.proyecto.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.app.models.Cliente;
import com.proyecto.app.repository.PersonalizadoClienteRepository;

@Service
public class PersonalizadoClienteServiceImpl {

	
	@Autowired
	private PersonalizadoClienteRepository personalizadoClienteRepository;
	
	public Cliente getClienteByDni(String dni) {
		return personalizadoClienteRepository.findByDni(dni);
	}
	
}
