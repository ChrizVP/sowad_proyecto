import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from 'src/app/components/Cliente/Modelo/Cliente';
import { Producto } from 'src/app/components/Producto/Modelo/Producto';
import { VentaDetProducto } from'../Modelo/VentaDetProducto';
import { VentaCabProducto } from '../Modelo/VentaCabProducto';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  

  constructor(private http:HttpClient) { }
  Url='http://localhost:8080/venta';
  

  getCliente(){
    return this.http.get<Cliente>(this.Url+"/cliente");
  }
  getVentaCabProducto(){
    return this.http.get<VentaCabProducto>(this.Url+"/ventaCabProducto");
  }
  getVentaDetProducto(){
    return this.http.get<VentaDetProducto[]>(this.Url+"/ventaDetProducto");
  }
  buscarCliente(dni:String){
    return this.http.get<Cliente>(this.Url+"/cliente/"+dni);
  }
  listadoProducto(){
    return this.http.get<Producto[]>(this.Url+"/listado/producto");
  }
  addProducto(producto_id:number,cantidad:number){
    return this.http.post<VentaDetProducto[]>(this.Url+"/add/producto/"+producto_id+"/"+cantidad,{});
  }
  
  realizarVenta(){
    return this.http.get<VentaCabProducto>(this.Url+"/realizarVenta");
  }
  cancelarVenta(){
    return this.http.get<VentaCabProducto>(this.Url+"/cancelarVenta")
  }
}
