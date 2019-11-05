import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  ListarCliente(){
    this.router.navigate(["listarCliente"]);
  }
  NuevoCliente(){
    this.router.navigate(["addCliente"]);
  }
  EditarCliente(){
    this.router.navigate(["editCliente"]);
  }

  ListarProducto(){
    this.router.navigate(["listarProducto"]);
  }
  NuevoProducto(){
    this.router.navigate(["addProducto"]);
  }
  EditarProducto(){
    this.router.navigate(["editProducto"]);
  }
  Venta(){
    this.router.navigate(["save-venta"]);
  }

}
