package com.proyecto.app.controllersRest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.proyecto.app.models.Cliente;
import com.proyecto.app.models.Producto;
import com.proyecto.app.models.VentaCabProducto;
import com.proyecto.app.models.VentaDetProducto;
import com.proyecto.app.repository.PersonalizadoClienteRepository;
import com.proyecto.app.service.ProductoService;
import com.proyecto.app.service.VentaCabProductoService;
import com.proyecto.app.service.VentaDetProductoService;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping({"/venta"})
public class VentaRestController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private  ProductoService ProductoService;
	
	@Autowired
	private VentaCabProductoService ventaCabProductoService;
	
	@Autowired
	private VentaDetProductoService ventaDetProductoService;
	
	@Autowired
	private PersonalizadoClienteRepository personalizadoClienteRepository;
	
	private Cliente cliente = new Cliente();
	
	@ElementCollection
	private List<VentaDetProducto> detProductos =  new ArrayList<VentaDetProducto>();
	
	private VentaCabProducto ventaCabProducto;
	
	@GetMapping(path = {"/cliente"})
	public Cliente getCliente() {
			if(cliente==null) {
				cliente= new Cliente();
			}
			return cliente;
	}
	
	@GetMapping(path = {"/ventaCabProducto"})
	public VentaCabProducto getVentaCabProducto() {
		if(ventaCabProducto==null){
			ventaCabProducto = new VentaCabProducto();
		}
		return ventaCabProducto;
	}
	
	@GetMapping(path = {"/ventaDetProducto"})
	public List<VentaDetProducto> getVentaDetProducto() {
		return detProductos;
	}
	
	@GetMapping(path = {"/cliente/{dni}"})
	public Cliente buscarCliente(@PathVariable("dni") String dni) {
			cliente = personalizadoClienteRepository.findByDni(dni);
			return cliente;
	}
	
	@GetMapping(path = {"/listado/producto"})
	public List<Producto> listarProducto(){
		return ProductoService.getAll();
	}
	
	@PostMapping(path = {"/add/producto/{producto_id}/{cantidad}"})
	public List<VentaDetProducto> addProducto(@PathVariable("producto_id") int producto_id, @PathVariable("cantidad") int cantidad ){
		if(ventaCabProducto==null){
			ventaCabProducto = new VentaCabProducto();
		}
		int cant = cantidad;
		Producto p = productoService.get(producto_id);
		ventaCabProducto.addProducto(p, cant);
		VentaDetProducto d = new VentaDetProducto(ventaCabProducto, p, cant);
		d.calcularSubTotal();
		float total = ventaCabProducto.getTotal(); 
		float subtotal = d.getSubTotal();
		ventaCabProducto.actualizarTotal(total, subtotal);
		detProductos.add(d);
		return detProductos;
	}
	
	
	@GetMapping(path = {"/realizarVenta"})
	public VentaCabProducto realizarVenta() {	
		if(detProductos.isEmpty()){
		}else {
			ventaCabProducto.setEstado("1");
			ventaCabProducto.setCliente_id(cliente.getCliente_id());
			ventaCabProductoService.save(ventaCabProducto);
			for (VentaDetProducto ventaDetProducto : detProductos) {
				ventaDetProductoService.save(ventaDetProducto);
			}
			detProductos.clear();
			ventaCabProducto = new VentaCabProducto();
			cliente = new Cliente();
		}
		//ventaCabProducto.setUser_id(user.getUser_id());
		return ventaCabProducto;
	}
	
	
	@GetMapping(path= {"/cancelarVenta"})
	public VentaCabProducto cancelarVenta() {
		detProductos.clear();
		ventaCabProducto = new VentaCabProducto();
		cliente = new Cliente();
		return ventaCabProducto;
	}
	
	
}
