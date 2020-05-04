import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FoncService {
public host:string ='http://localhost:8003'
  constructor(private http: HttpClient) {
  }
  public getFonc(url){
    return this.http.get(this.host + url);
  }
}
