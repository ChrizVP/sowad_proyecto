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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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
	private Float total=0.0f;
	
	@Column
	private int cliente_id;
	
	@Column
	private int user_id;
	
	@Column
	private Date fecha;
	
	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}
	
	
	
	public VentaCabProducto() {
	}



	@JsonBackReference
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




	public int getCliente_id() {
		return cliente_id;
	}



	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}



	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
            if (ventaDetProducto.getProducto().getProducto_id() == producto.getProducto_id() ) {
            	System.out.println("HASTA AQUI LLEGUE :V");
            	System.out.println(producto.getProducto_id());
            	System.out.println(ventaDetProducto.getProducto().getProducto_id());
               
                
                ventaDetProducto.getProducto().getOrdenesCompra().remove(ventaDetProducto);
                iterator.remove();
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
