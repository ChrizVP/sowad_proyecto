package com.proyecto.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto.app.commons.GenericService;
import com.proyecto.app.models.VentaDetProducto;
import com.proyecto.app.repository.VentaDetProductoRepository;
import com.proyecto.app.service.VentaDetProductoService;

@Service
public class VentaDetProductoServiceImpl extends GenericService<VentaDetProducto, Integer> implements VentaDetProductoService{

	@Autowired
	private VentaDetProductoRepository VentaDetProductoRepository;
	
	@Override
	public CrudRepository<VentaDetProducto, Integer> getDao() {
		
		return VentaDetProductoRepository;
	}

}
