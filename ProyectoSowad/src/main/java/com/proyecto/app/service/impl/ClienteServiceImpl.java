package com.proyecto.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto.app.commons.GenericService;
import com.proyecto.app.models.Cliente;
import com.proyecto.app.repository.ClienteRepository;
import com.proyecto.app.service.ClienteService;

@Service
public class ClienteServiceImpl extends GenericService<Cliente, Integer> implements ClienteService {

	@Autowired
	private ClienteRepository ClienteRepository;
	
	@Override
	public CrudRepository<Cliente, Integer> getDao() {
		
		return ClienteRepository;
	}

	public List<Cliente> getClienteByDni(String dni) {
		return getClienteByDni(dni);
	}
}
