import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ListarClienteComponent } from './components/Cliente/listarCliente/listarCliente.component';
import { AddClienteComponent } from './components/Cliente/addCliente/addCliente.component';
import { EditClienteComponent } from './components/Cliente/editCliente/editCliente.component';

import { ListarProductoComponent } from './components/Producto/listarProducto/listarProducto.component';
import { AddProductoComponent } from './components/Producto/addProducto/addProducto.component';
import { EditProductoComponent } from './components/Producto/editProducto/editProducto.component';

import {FormsModule}from'@angular/forms';
import {ClienteService}from './components/Cliente/Service/Cliente.service';
import {ProductoService}from './components/Producto/Service/Producto.service';
import { LoginService } from './components/Login/Service/login.service';

import {HttpClientModule} from '@angular/common/http';
import { SidebarComponent } from './components/Share/sidebar/sidebar.component';
import { TopbarComponent } from './components/Share/topbar/topbar.component';
import { FooterComponent } from './components/Share/footer/footer.component';
import { LogoutModalComponent } from './components/Share/logout-modal/logout-modal.component';
import { PageContentComponent } from './components/Share/page-content/page-content.component';

import { AddProductosComponent } from './components/Venta/add-productos/add-productos.component';
import { ListarProductosComponent } from './components/Venta/listar-productos/listar-productos.component';
import { SaveVentaComponent } from './components/Venta/save-venta/save-venta.component';
import { FindClienteComponent } from './components/Venta/find-cliente/find-cliente.component';
import { LoginComponent } from './components/Login/Login/login.component';
import { HomeComponent } from './components/Share/home/home.component';


@NgModule({
  declarations: [
    AppComponent,
    ListarClienteComponent,
    AddClienteComponent,
    EditClienteComponent,
    ListarProductoComponent,
    AddProductoComponent,
    EditProductoComponent,
    SidebarComponent,
    TopbarComponent,
    FooterComponent,
    LogoutModalComponent,
    PageContentComponent,
    AddProductosComponent,
    ListarProductosComponent,
    SaveVentaComponent,
    FindClienteComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ClienteService,ProductoService,LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
