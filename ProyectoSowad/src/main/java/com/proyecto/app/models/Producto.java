package com.proyecto.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Producto implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int producto_id;
	@Column
	private String nombre;
	@Column
	private String color;
	@Column
	private int cantidad;
	@Column
	private float precio;
	
	@JsonBackReference
	@OneToMany(mappedBy = "producto", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<VentaDetProducto> ordenesCompra = new ArrayList<>();
	
	 
	
	public Producto() {
	}
	
	
	
	
	public Producto(String nombre, String color, int cantidad, float precio) {
		this.nombre = nombre;
		this.color = color;
		this.cantidad = cantidad;
		this.precio = precio;
	}




	public int getProducto_id() {
		return producto_id;
	}
	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public List<VentaDetProducto> getOrdenesCompra() {
		return ordenesCompra;
	}
	public void setOrdenesCompra(List<VentaDetProducto> ordenesCompra) {
		this.ordenesCompra = ordenesCompra;
	}
	
	

	
}
