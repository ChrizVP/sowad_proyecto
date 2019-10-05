package com.proyecto.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.app.models.Cliente;
import com.proyecto.app.service.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/cliente/home")
	public String home(Model model) {
		model.addAttribute("List", clienteService.getAll());
		return"homeCliente";
	}
	
	@GetMapping("/cliente/edit/{id}")
	public String showEdit(@PathVariable("id")Integer id, Model model) {
		if(id !=null && clienteService.get(id)!=null){
		model.addAttribute("cliente", clienteService.get(id));
		return "save";
		}else {	
			return "error";
		}
	}
	
	@PostMapping("/cliente/edit/{id}")
	public String saveEdit(Cliente cliente, Model model) {
		clienteService.save(cliente);
		return "redirect:/cliente/home";
	}
	
	
	@GetMapping("/cliente/new")
	public String saveNew(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "save";
	}
	
	@PostMapping("/cliente/save/new")
	public String save(Cliente cliente, Model model) {
		clienteService.save(cliente);
		return "redirect:/cliente/home";
		
	}
	@GetMapping("/cliente/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {
		
		clienteService.deleted(id);
		return "redirect:/cliente/home";
	}
	
}
