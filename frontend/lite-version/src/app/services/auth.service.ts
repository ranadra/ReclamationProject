import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private host: string = "http://localhost:8003";
  private JwToken = null;
  private user;
  constructor(private http: HttpClient) { }
  public login(user) {
    return this.http.post(this.host + "/login", user, {observe: "response"});
  }
  public register(user) {
    return this.http.post(this.host + "/register", user, {observe: "response"});

  }
      getCurrentUser()
      {
          localStorage.getItem('user');
      }
  loadToken(){
    this.JwToken = localStorage.getItem('token');
  }
  saveUser(user){
localStorage.setItem( 'user', user);
  }
   saveToken(jwt: string) {
      localStorage.setItem('token', jwt);
  }
}
