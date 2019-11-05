import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductoService } from 'src/app/components/Producto/Service/Producto.service';
import { Producto } from '../Modelo/Producto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editProducto',
  templateUrl: './editProducto.component.html',
  styleUrls: ['./editProducto.component.css']
})
export class EditProductoComponent implements OnInit {

  producto:Producto = new Producto();
  constructor(private router:Router, private service:ProductoService) { }

  ngOnInit() {
    this.EditarProducto();
  }

  EditarProducto(){
    let producto_id=localStorage.getItem("producto_id");
    this.service.getProductoId(+producto_id)
    .subscribe(data=>{
      this.producto=data;
    })
  }

  ActualizarProducto(producto:Producto){
    
    this.service.updateProducto(producto)
    .subscribe(data=>{
      this.producto=data;
      this.showModal();
      this.router.navigate(["listarProducto"]);
    })
  }

  showModal(){
    Swal.fire({
      position: 'center',
      type: 'success',
      title: 'Actualizado con Exito!',
      showConfirmButton: false,
      timer: 1500
    });
  }
  

}
