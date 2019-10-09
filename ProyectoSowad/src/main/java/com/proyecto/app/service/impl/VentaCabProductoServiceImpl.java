package com.proyecto.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto.app.commons.GenericService;
import com.proyecto.app.models.VentaCabProducto;
import com.proyecto.app.repository.VentaCabProductoRepository;
import com.proyecto.app.service.VentaCabProductoService;

@Service
public class VentaCabProductoServiceImpl extends GenericService<VentaCabProducto, Integer> implements VentaCabProductoService{

	@Autowired
	private VentaCabProductoRepository CabProductoRepository;
	
	@Override
	public CrudRepository<VentaCabProducto, Integer> getDao() {
		
		return CabProductoRepository;
	}
}
