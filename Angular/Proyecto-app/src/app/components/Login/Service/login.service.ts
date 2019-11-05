import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn:'root'
})

export class LoginService {

  constructor(private http:HttpClient) {}
  Url='http://localhost:8080/admin';



  login(username:string, password:string){
    const headers = new HttpHeaders({Authorization:'Basic'+btoa(username+":"+password)})
    return this.http.get(this.Url+"/se",{headers, responseType:'text'as 'json'}); 
  }

  loginUser(user: any): Observable<any>{
    const headers = new HttpHeaders({'Access-Control-Allow-Origin':'*'});
    return this.http.post(this.Url, user,{headers:headers});
  }

}
