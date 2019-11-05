import { Component, OnInit } from '@angular/core';
import { LoginService } from '../Service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username:string;
  password:string;
  message:any
  public user: any= {};
  constructor(private service:LoginService, private router:Router) { }

  ngOnInit() {
  }


  doLogin(){
    
    this.service.login(this.username, this.password)
    .subscribe(data=>{
      this.message =data;
      this.router.navigate(["/home"])
    });
  }

  loginUser(user:any){
    this.service.loginUser(user).subscribe((response)=>{
      if(response){
        if(response.token){
          localStorage.setItem('currentUser',JSON.stringify(response));
          if(response.user.role =='ADMIN'){
            this.router.navigate(['/home'])
         }
        }
      }
    })  
  
  } 
  

}
