package com.proyecto.app.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;






@Entity
@Getter
@Setter
@Component
public class VentaDetProducto  implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ventaDetProducto_id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private VentaCabProducto ventaCabProducto;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;
	
	@Column
	public int cantidad;
	
	@Column
	public Float subTotal;
	


	public VentaDetProducto() {
		
	}


	public VentaDetProducto( VentaCabProducto ventaCabProducto, Producto producto, int cantidad) {
		
		this.ventaCabProducto = ventaCabProducto;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        VentaDetProducto that = (VentaDetProducto) o;
        return Objects.equals(ventaCabProducto, that.ventaCabProducto) &&
               Objects.equals(producto, that.producto);
    }

	public int getVentaDetProducto_id() {
		return ventaDetProducto_id;
	}


	public void setVentaDetProducto_id(int ventaDetProducto_id) {
		this.ventaDetProducto_id = ventaDetProducto_id;
	}


	public void calcularSubTotal() {
		this.subTotal = this.cantidad*this.producto.getPrecio();
	}



	public VentaCabProducto getVentaCabProducto() {
		return ventaCabProducto;
	}


	public void setVentaCabProducto(VentaCabProducto ventaCabProducto) {
		this.ventaCabProducto = ventaCabProducto;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Float getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(ventaCabProducto, producto);
    }
}
