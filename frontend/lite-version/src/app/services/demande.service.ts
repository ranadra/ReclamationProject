import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DemandeService {
  private host: string = "http://localhost:8003";
  private JwToken = null;
  constructor(private http: HttpClient) { }
  getDemandes():Observable<any>{
    if (this.JwToken == null ) this.loadToken();
    return this.http.get(this.host+"/demandes",
    {headers: new HttpHeaders({'authorization':this.JwToken})})
  }
  loadToken(){
    this.JwToken = localStorage.getItem('token');
  }
}
