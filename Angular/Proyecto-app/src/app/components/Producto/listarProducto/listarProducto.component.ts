import { Component, OnInit } from '@angular/core';
import{ProductoService}from'src/app/components/Producto/Service/Producto.service';
import { Router } from '@angular/router';
import { Producto } from 'src/app/components/Producto/Modelo/Producto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listarProducto',
  templateUrl: './listarProducto.component.html',
  styleUrls: ['./listarProducto.component.css']
})
export class ListarProductoComponent implements OnInit {

  productos:Producto[];
  constructor(private service:ProductoService, private router:Router) { }

  ngOnInit() {
    this.service.getProducto()
    .subscribe(data=>{
      this.productos=data;
    })
  }

  EditarProducto(producto:Producto):void{
    localStorage.setItem("producto_id",producto.producto_id.toString());
    this.router.navigate(["editProducto"]);
  }
  
  NuevoProducto(){
    this.router.navigate(["addProducto"]);
  }

  showModalDelete(producto:Producto){

    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: 'Estas Seguro?',
      text: "No podras Revertir esto!",
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, Eliminalo!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        swalWithBootstrapButtons.fire(
          
          'Eliminado!',
          'Su producto fue eliminado.',
          'success'
        )
        this.service.deleteProducto(producto)
        .subscribe(data=>{
        this.productos=this.productos.filter(p=>p!==producto);
        })
      } else if (
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cancelado',
          'Tu producto esta seguro :)',
          'error'
        )
      }
    })
  }


}
