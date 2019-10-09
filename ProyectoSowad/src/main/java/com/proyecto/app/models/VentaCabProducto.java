package com.proyecto.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Entity
@Component
public class VentaCabProducto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ventaCab_id;
	
	@Column
	private String estado;
	
	@Column
	private Float total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user_id;
	
	@Column
	private Date fecha;
	
	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}
	
	
	
	public VentaCabProducto() {
	}


	
	@OneToMany(mappedBy = "ventaCabProducto", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<VentaDetProducto> productos = new ArrayList<>();
	
	public VentaCabProducto(Float total) {
        this.total = total;
    }

	public void actualizarTotal(Float total, Float subtotal) {
		this.total = (float)(total + subtotal);
	}
	
	public int getVentaCab_id() {
		return ventaCab_id;
	}

	public void setVentaCab_id(int ventaCab_id) {
		this.ventaCab_id = ventaCab_id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void addProducto(Producto producto, int cantidad) {
	 	VentaDetProducto ventaDetProducto = new VentaDetProducto(this, producto, cantidad);
        productos.add(ventaDetProducto);
        producto.getOrdenesCompra().add(ventaDetProducto);
    }
	
	public void removeProducto(Producto producto) {
        for (Iterator<VentaDetProducto> iterator = productos.iterator(); 
             iterator.hasNext(); ) {
        	VentaDetProducto ventaDetProducto = iterator.next();
 
            if (ventaDetProducto.getVentaCabProducto().equals(this) &&
            		ventaDetProducto.getProducto().equals(producto)) {
                iterator.remove();
                ventaDetProducto.getProducto().getOrdenesCompra().remove(ventaDetProducto);
                ventaDetProducto.setVentaCabProducto(null);
                ventaDetProducto.setProducto(null);
            }
        }
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        VentaCabProducto ventaCabProducto = (VentaCabProducto) o;
        return Objects.equals(total, ventaCabProducto.total) &&
        	   Objects.equals(fecha, ventaCabProducto.fecha);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(total, fecha);
    }
	
}
