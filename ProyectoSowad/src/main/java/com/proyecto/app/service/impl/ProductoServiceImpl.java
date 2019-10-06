package com.proyecto.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto.app.commons.GenericService;
import com.proyecto.app.models.Producto;
import com.proyecto.app.repository.ProductoRepository;
import com.proyecto.app.service.ProductoService;

@Service
public class ProductoServiceImpl extends GenericService<Producto, Integer> implements ProductoService {

	
	@Autowired
	private ProductoRepository ProductoRepository;
	
	@Override
	public CrudRepository<Producto, Integer> getDao() {
		return ProductoRepository;
	}

}
