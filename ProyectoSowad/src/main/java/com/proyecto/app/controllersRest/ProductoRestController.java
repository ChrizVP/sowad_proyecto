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

import com.proyecto.app.models.Producto;
import com.proyecto.app.service.ProductoService;


@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping({"/producto"})
public class ProductoRestController {

	@Autowired
	private  ProductoService ProductoService;
	
	@GetMapping
	public List<Producto> listar() {
		System.out.println(ProductoService.getAll());
		return ProductoService.getAll();
	}
	
	@PostMapping
	public Producto agregar(@RequestBody Producto p) {
		return ProductoService.save(p);
		
	}
	
	@GetMapping(path = {"/{producto_id}"})
	public Producto listarId(@PathVariable("producto_id") int producto_id) {
			return ProductoService.get(producto_id);
	}
	
	@PutMapping(path = {"/{producto_id}"})
	public Producto editar(@RequestBody Producto producto, @PathVariable("producto_id") int producto_id) {
		producto.setProducto_id(producto_id);
		return ProductoService.save(producto);
	}
	
	@DeleteMapping(path= {"/{producto_id}"})
	public void delete(@PathVariable("producto_id") int producto_id) {
		ProductoService.deleted(producto_id);
	}
	
	
}
