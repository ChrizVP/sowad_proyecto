package com.proyecto.app.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;





@Embeddable
public class VentaOrden implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column
	private int ventaCabProducto_id;
	 
    @Column
    private int producto_id;

	public VentaOrden(int ventaCabProducto_id, int producto_id) {
		this.ventaCabProducto_id = ventaCabProducto_id;
		this.producto_id = producto_id;
	}

	public int getVentaCabProducto_id() {
		return ventaCabProducto_id;
	}

	public void setVentaCabProducto_id(int ventaCabProducto_id) {
		this.ventaCabProducto_id = ventaCabProducto_id;
	}

	public int getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        VentaOrden that = (VentaOrden) o;
        return Objects.equals(ventaCabProducto_id, that.ventaCabProducto_id) && 
               Objects.equals(producto_id, that.producto_id);
    }
    
	@Override
    public int hashCode() {
        return Objects.hash(ventaCabProducto_id, producto_id);
    }
	
}
