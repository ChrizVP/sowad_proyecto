package com.proyecto.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.app.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

	//List<Cliente> findByDni(String dni);
	Cliente findByDni(String dni);
}
