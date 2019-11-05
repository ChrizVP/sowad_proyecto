import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VentaService } from 'src/app/components/Venta/Servicio/venta.service';
import { Cliente } from 'src/app/components/Cliente/Modelo/Cliente';
import { Producto } from 'src/app/components/Producto/Modelo/Producto';
import { VentaDetProducto } from '../Modelo/VentaDetProducto';
import { VentaCabProducto } from '../Modelo/VentaCabProducto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-save-venta',
  templateUrl: './save-venta.component.html',
  styleUrls: ['./save-venta.component.css']
})
export class SaveVentaComponent implements OnInit {

  productos:Producto[];
  ventaDetProducto:VentaDetProducto[];
  cliente:Cliente = new Cliente();
  ventaCabProducto:VentaCabProducto=new VentaCabProducto();
  constructor(private router:Router, private service:VentaService) { }

  ngOnInit() {
    this.GetVentaCabProducto();
    this.GetCliente();
    this.GetVentaDetProducto();
  }

  GetCliente(){
    this.service.getCliente()
    .subscribe(data=>{
      this.cliente=data;
    })
  }

  GetVentaCabProducto(){
    this.service.getVentaCabProducto()
    .subscribe(data=>{
      this.ventaCabProducto=data;
    })
  }

  GetVentaDetProducto(){
    this.service.getVentaDetProducto()
    .subscribe(data=>{
      this.ventaDetProducto=data;
    })
  }

  BuscarCliente(cliente:Cliente):void{
    
    localStorage.setItem("dni",cliente.dni.toString());
    let dni=localStorage.getItem("dni");
    
    this.service.buscarCliente(dni)
    .subscribe(data=>{
      this.cliente=data;
      if(data == null){
        this.showModalNoEncontrado();
        this.cliente = new Cliente();
      }else{
        this.showModalEncontrado();
      }
    })
    
  }

  ListarProducto() {
    this.service.listadoProducto()
    .subscribe(data=>{
      this.productos=data;
    })
  }

  AddProducto(producto:Producto,cantidad:number){
    
    localStorage.setItem("producto_id",producto.producto_id.toString());
    let producto_id=localStorage.getItem("producto_id");
    this.service.addProducto(+producto_id,+cantidad)
    .subscribe(data=>{
        this.ventaDetProducto=data; 
        this.showModalAgregar();
        this.GetVentaCabProducto();
        
    })
    
  }
  
  RealizarVenta(){
    this.service.realizarVenta()
    .subscribe(data=>{
      this.ventaCabProducto=data;
      this.cliente = new Cliente();
      this.ventaDetProducto = [];
    })
  }

  CancelarVenta(){
    this.service.cancelarVenta()
    .subscribe(data=>{
      this.ventaCabProducto=data;
    });
    this.cliente = new Cliente();
    this.ventaDetProducto = [];
  }

  showModalAgregar(){
    Swal.fire({
      position: 'center',
      type: 'success',
      title: 'Agregado con Exito!',
      showConfirmButton: false,
      timer: 1000
    });
  }

  showModalEncontrado(){
    Swal.fire({
      position: 'center',
      type: 'success',
      title: 'Encontrado con Exito!',
      showConfirmButton: false,
      timer: 1500
    });
  }

  showModalNoEncontrado(){
    Swal.fire({
      position: 'center',
      type: 'error',
      title: 'Fallo al Encontrar!',
      showConfirmButton: false,
      timer: 1500
    });
  }
}
