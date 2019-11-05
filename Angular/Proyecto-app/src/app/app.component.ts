import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Proyecto-app';

  constructor(private router:Router){}

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
