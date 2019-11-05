import { Component, OnInit } from '@angular/core';
import{ClienteService}from'src/app/components/Cliente/Service/Cliente.service'
import { Router } from '@angular/router';
import { Cliente } from 'src/app/components/Cliente/Modelo/Cliente';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listarCliente',
  templateUrl: './listarCliente.component.html',
  styleUrls: ['./listarCliente.component.css']
})


export class ListarClienteComponent implements OnInit {

  clientes:Cliente[];
  constructor(private service:ClienteService, private router:Router) { }

  ngOnInit() {
    this.service.getCliente()
    .subscribe(data=>{
      this.clientes=data;
    })
  }
  EditarCliente(cliente:Cliente):void{
    localStorage.setItem("cliente_id",cliente.cliente_id.toString());
    this.router.navigate(["editCliente"]);
  }

  NuevoCliente(){
    this.router.navigate(["addCliente"]);
  }
  
  showModalDelete(cliente:Cliente){

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
          'Su cliente fue eliminado.',
          'success'
        )
        this.service.deleteCliente(cliente)
          .subscribe(data=>{
          this.clientes=this.clientes.filter(p=>p!==cliente);
        })
      } else if (
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cancelado',
          'Tu cliente esta seguro :)',
          'error'
        )
      }
    })
  }

}
