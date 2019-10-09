package com.proyecto.app.controllers;

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

import com.proyecto.app.models.Producto;
import com.proyecto.app.models.VentaCabProducto;
import com.proyecto.app.models.VentaDetProducto;
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
	private ProductoService productoService;
	
	private VentaCabProducto ventaCabProducto;
	
	@ElementCollection
	private List<VentaDetProducto> detProductos =  new ArrayList<VentaDetProducto>();
	
	@RequestMapping(value="/from",method=RequestMethod.GET)
	public String tienda(Model model){
		model.addAttribute("titulo","Tienda Online");
		model.addAttribute("productos",productoService.getAll());
		return "from";
	}
	
	
	@RequestMapping(value="/from/{id}",method=RequestMethod.POST)
	public String guardar(@Valid Producto producto ,BindingResult result, Model model, SessionStatus status, RedirectAttributes f, @PathVariable("id") int producto_id, @RequestParam("txtCant") int cantidad ) {	
		if(ventaCabProducto==null){
			ventaCabProducto = new VentaCabProducto();
		}
		
		int cant = cantidad;
		Producto p = productoService.get(producto_id);
		
		ventaCabProducto.addProducto(p, cant);
		
		
		VentaDetProducto d= new VentaDetProducto(ventaCabProducto, p, cant);
		
		d.calcularSubTotal();
		float total = ventaCabProducto.getTotal(); float subtotal = d.getSubTotal();
		ventaCabProducto.actualizarTotal(total, subtotal);
		
		detProductos.add(d);
		
		return "redirect:/venta";
		
	}
	
	
	@RequestMapping(value="/venta",method=RequestMethod.GET)
	public String listar(Model model){
		model.addAttribute("titulo","venta");
		model.addAttribute("compras", detProductos);
		model.addAttribute("total", ventaCabProducto);
		return "venta";
	}
	
	@RequestMapping(value="/RealizarVenta")
	public String realizarVenta(RedirectAttributes f) {	
		if(detProductos.isEmpty()){
			f.addFlashAttribute("error","El detalle venta no puede estar vacio!");
			return "redirect:/venta";
		}
		
		ventaCabProductoService.save(ventaCabProducto);
		for (VentaDetProducto ventaDetProducto : detProductos) {
			ventaDetProductoService.save(ventaDetProducto);
		}
		f.addFlashAttribute("success","Grabado con exito");
		detProductos.clear();
		ventaCabProducto = new VentaCabProducto();
		return "redirect:/from";
		
	}
	
}
