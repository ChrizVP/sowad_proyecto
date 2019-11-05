import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from '../Modelo/Cliente';


@Injectable()
export class ClienteService {

  
  constructor(private http:HttpClient) { }
  Url='http://localhost:8080/cliente';

  getCliente(){
    return this.http.get<Cliente[]>(this.Url);
  }
  createCliente(cliente:Cliente){
    return this.http.post<Cliente>(this.Url,cliente);
  }
  getClienteId(cliente_id:number){
    return this.http.get<Cliente>(this.Url+"/"+cliente_id);
  }
  updateCliente(cliente:Cliente){
    return this.http.put<Cliente>(this.Url+"/"+cliente.cliente_id,cliente)
  }
  deleteCliente(cliente:Cliente){
    return this.http.delete<Cliente>(this.Url+"/"+cliente.cliente_id)
  }
}
