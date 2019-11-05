package com.proyecto.app.controllersRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.app.models.Cliente;
import com.proyecto.app.service.ClienteService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping({"/cliente"})
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente>listar() {
		return clienteService.getAll();
	}
	
	@PostMapping
	public Cliente agregar(@RequestBody Cliente c) {
		return clienteService.save(c);
		
	}
	
	@GetMapping(path = {"/{cliente_id}"})
	public Cliente listarId(@PathVariable("cliente_id") int cliente_id) {
			return clienteService.get(cliente_id);
	}
	
	@PutMapping(path = {"/{cliente_id}"})
	public Cliente editar(@RequestBody Cliente cliente, @PathVariable("cliente_id") int cliente_id) {
		cliente.setCliente_id(cliente_id);;
		return clienteService.save(cliente);
	}
	
	@DeleteMapping(path= {"/{cliente_id}"})
	public void delete(@PathVariable("cliente_id") int cliente_id) {
		clienteService.deleted(cliente_id);
	}
	
}
