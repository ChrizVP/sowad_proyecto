import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }
  ListarCliente(){
    this.router.navigate(["listarCliente"]);
  }
  
  ListarProducto(){
    this.router.navigate(["listarProducto"]);
  }

  Venta(){
    this.router.navigate(["save-venta"]);
  }

  
}
