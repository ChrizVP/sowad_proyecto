import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListarClienteComponent } from './components/Cliente/listarCliente/listarCliente.component';
import { AddClienteComponent } from './components/Cliente/addCliente/addCliente.component';
import { EditClienteComponent } from './components/Cliente/editCliente/editCliente.component';

import { ListarProductoComponent } from './components/Producto/listarProducto/listarProducto.component';
import { AddProductoComponent } from './components/Producto/addProducto/addProducto.component';
import { EditProductoComponent } from './components/Producto/editProducto/editProducto.component';

import { SaveVentaComponent } from './components/Venta/save-venta/save-venta.component';
import { LoginComponent } from './components/Login/Login/login.component';
import { HomeComponent } from './components/Share/home/home.component';
import {AuthGuard} from'./auth.guard';

const routes: Routes = [

  {path:'**',redirectTo:'login',pathMatch:'full'},

  {path:'listarCliente',component:ListarClienteComponent},
  {path:'addCliente',component:AddClienteComponent},
  {path:'editCliente',component:EditClienteComponent},

  {path:'listarProducto',component:ListarProductoComponent},
  {path:'addProducto',component:AddProductoComponent},
  {path:'editProducto',component:EditProductoComponent},

  {path:'save-venta',component:SaveVentaComponent},

  {path:'login', component:LoginComponent},

  {path:'home', component:HomeComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
