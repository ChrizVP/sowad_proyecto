package com.proyecto.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.proyecto.app.models.Producto;
import com.proyecto.app.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@RequestMapping("/producto/home")
	public String home(Model model) {
		model.addAttribute("List", productoService.getAll());
		return"productoHome";
	}
	
	@GetMapping("/producto/edit/{id}")
	public String showEdit(@PathVariable("id")Integer id, Model model) {
		if(id !=null && productoService.get(id)!=null){
		model.addAttribute("producto", productoService.get(id));
		return "productoEdit";
		}else {	
			return "404";
		}
	}
	
	@PostMapping("/producto/edit/{id}")
	public String saveEdit(Producto producto, Model model) {
		productoService.save(producto);
		return "redirect:/producto/home";
	}
	
	
	@GetMapping("/producto/new")
	public String saveNew(Model model) {
		model.addAttribute("producto", new Producto());
		return "productoSave";
	}
	
	@PostMapping("/producto/save/new")
	public String save(Producto producto, Model model) {
		productoService.save(producto);
		return "redirect:/producto/home";
		
	}
	@GetMapping("/producto/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {
		
		productoService.deleted(id);
		return "redirect:/producto/home";
	}
	
	
}
