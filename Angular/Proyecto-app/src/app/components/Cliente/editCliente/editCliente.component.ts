import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClienteService } from 'src/app/components/Cliente/Service/Cliente.service';
import { Cliente } from 'src/app/components/Cliente/Modelo/Cliente';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editCliente',
  templateUrl: './editCliente.component.html',
  styleUrls: ['./editCliente.component.css']
})
export class EditClienteComponent implements OnInit {

  cliente:Cliente = new Cliente();
  constructor(private router:Router, private service:ClienteService) { }

  ngOnInit() {
    this.EditarCliente();
  }

  EditarCliente(){
    let cliente_id=localStorage.getItem("cliente_id");
    this.service.getClienteId(+cliente_id)
    .subscribe(data=>{
      this.cliente=data;
    })
  }

  ActualizarCliente(cliente:Cliente){
    this.service.updateCliente(cliente)
    .subscribe(data=>{
      this.cliente=data;
      this.showModal();
      this.router.navigate(["listarCliente"]);
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
