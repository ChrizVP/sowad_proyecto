import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductoService } from 'src/app/components/Producto/Service/Producto.service';
import { Producto } from '../Modelo/Producto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-addProducto',
  templateUrl: './addProducto.component.html',
  styleUrls: ['./addProducto.component.css']
})
export class AddProductoComponent implements OnInit {

  constructor(private router:Router, private service:ProductoService) { }
  producto:Producto = new Producto();
  ngOnInit() {
  }

  GuardarProducto(){
    this.service.createProducto(this.producto)
    .subscribe(data=>{
      this.showModal();
      this.router.navigate(["listarProducto"]);
    })
  }

  showModal(){
    Swal.fire({
      position: 'center',
      type: 'success',
      title: 'Guardado con exito!',
      showConfirmButton: false,
      timer: 1500
    });
  }

}
