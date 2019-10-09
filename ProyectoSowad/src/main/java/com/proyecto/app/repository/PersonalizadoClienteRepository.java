package com.proyecto.app.repository;



import org.springframework.data.repository.CrudRepository;

import com.proyecto.app.models.Cliente;


public interface PersonalizadoClienteRepository extends CrudRepository<Cliente, Integer>{
	Cliente findByDni(String dni);
}
