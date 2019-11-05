import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClienteService } from 'src/app/components/Cliente/Service/Cliente.service';
import {Cliente} from 'src/app/components/Cliente/Modelo/Cliente';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-addCliente',
  templateUrl: './addCliente.component.html',
  styleUrls: ['./addCliente.component.css']
})
export class AddClienteComponent implements OnInit {

  constructor(private router:Router, private service:ClienteService) { }
  cliente:Cliente = new Cliente();


  ngOnInit() {
  }
  GuardarCliente(){
    this.service.createCliente(this.cliente)
    .subscribe(data=>{
      this.showModal();
      this.router.navigate(["listarCliente"]);
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
