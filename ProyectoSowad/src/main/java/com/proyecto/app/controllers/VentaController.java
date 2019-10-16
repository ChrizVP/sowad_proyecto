package com.proyecto.app.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.app.models.Cliente;
import com.proyecto.app.models.Producto;
import com.proyecto.app.models.User;
import com.proyecto.app.models.VentaCabProducto;
import com.proyecto.app.models.VentaDetProducto;
import com.proyecto.app.repository.ClienteRepository;
import com.proyecto.app.repository.UserRepository;
import com.proyecto.app.repository.VentaCabProductoRepository;
import com.proyecto.app.service.ProductoService;
import com.proyecto.app.service.VentaCabProductoService;
import com.proyecto.app.service.VentaDetProductoService;

@Controller
@SessionAttributes("Venta")
public class VentaController {

	
	@Autowired
	private VentaCabProductoService ventaCabProductoService;
	
	
	@Autowired
	private VentaDetProductoService ventaDetProductoService;
	
	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private VentaCabProductoRepository VentaCabProductoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private Cliente cliente = new Cliente();
	private User user;
	private VentaCabProducto ventaCabProducto;
	
	
	@ElementCollection
	private List<VentaDetProducto> detProductos =  new ArrayList<VentaDetProducto>();
	
	@RequestMapping("/reporte/cotizacion")
	public String home(Model model) {
		model.addAttribute("List", ventaCabProductoService.getAll());
		return"reporte";
	}
	
	
	@RequestMapping("/usuario/venta")
	public String user(Principal principal) {
		user = UserRepository.findByUsername(principal.getName());
			return "redirect:/RealizarVenta";
	}
	
	
	@RequestMapping("/cotizacion/buscarCliente")
	public String buscarcliente(@RequestParam("dni") String dni) {
		if(dni=="") {
		}else {
			cliente = clienteRepository.findByDni(dni);
			if(cliente==null) {
				cliente =  new Cliente();
			}
		}
		return "redirect:/cotizacion";
	}
	
	
	@RequestMapping(value="/cotizacion",method=RequestMethod.GET)
	public String producto(Model model){
		if(ventaCabProducto==null) {
			ventaCabProducto = new VentaCabProducto();
			model.addAttribute("total", ventaCabProducto);
			model.addAttribute("compras", detProductos);
		}else {
			model.addAttribute("total", ventaCabProducto);
			model.addAttribute("compras", detProductos);
		}
		model.addAttribute("cliente", cliente);
		model.addAttribute("productos",productoService.getAll());
		return "cotizacion";
	}
	
	
	@RequestMapping(value="/add/cotizacion/{id}",method=RequestMethod.POST)
	public String guardar(@Valid Producto producto ,BindingResult result, Model model, SessionStatus status, RedirectAttributes f,@PathVariable("id") int idProducto, @RequestParam("cantidad") int cantidad) {	
		if(ventaCabProducto==null){
			ventaCabProducto = new VentaCabProducto();
		}
		int cant = cantidad;
		Producto p = productoService.get(idProducto);
		ventaCabProducto.addProducto(p, cant);
		VentaDetProducto d= new VentaDetProducto(ventaCabProducto, p, cant);
		d.calcularSubTotal();
		float total = ventaCabProducto.getTotal(); 
		float subtotal = d.getSubTotal();
		ventaCabProducto.actualizarTotal(total, subtotal);
		ventaCabProducto.setCliente_id(cliente.getCliente_id());
		detProductos.add(d);
		return "redirect:/cotizacion";
		
	}
	
	
	@RequestMapping(value="/venta/{id}",method=RequestMethod.GET)
	public String remove(@PathVariable("id") int producto_id){
		Producto p1 = new Producto();
		p1 = productoService.get(producto_id);
		ventaCabProducto.removeProducto(p1);
			return "redirect:/cotizacion";
	}
	
	@RequestMapping(value="/RealizarVenta")
	public String realizarVenta(RedirectAttributes f) {	
		if(detProductos.isEmpty()){
			f.addFlashAttribute("error","El detalle venta no puede estar vacio!");
			return "redirect:/cotizacion";
		}
		ventaCabProducto.setUser_id(user.getUser_id());
		ventaCabProductoService.save(ventaCabProducto);
		for (VentaDetProducto ventaDetProducto : detProductos) {
			ventaDetProductoService.save(ventaDetProducto);
		}
		f.addFlashAttribute("success","Grabado con exito");
		detProductos.clear();
		ventaCabProducto = new VentaCabProducto();
		cliente = new Cliente();
		return "redirect:/cotizacion";
		
	}
	
	@RequestMapping("/cotizacion/cancelar")
	public String cancelar() {
		detProductos.clear();
		ventaCabProducto = new VentaCabProducto();
		cliente = new Cliente();
		return "redirect:/";
	}
	
	
}
