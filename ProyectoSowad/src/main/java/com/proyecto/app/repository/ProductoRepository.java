package com.proyecto.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.app.models.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}
