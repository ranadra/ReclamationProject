import { Router } from '@angular/router';
import { AuthService } from './../../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-basic-reg',
  templateUrl: './basic-reg.component.html',
  styleUrls: ['./basic-reg.component.scss']
})
export class BasicRegComponent implements OnInit {
user;
mode:number = 0;
errormessage: string;
  constructor(private authService:AuthService ,  private router: Router ) { }

  ngOnInit() {
  }
    public OnRegister(user){
  this.authService.register(user)
  .subscribe(data => {
    this.user = data;
    this.mode = 1;
    this.router.navigateByUrl("/login")
  }, err => {
    this.mode = 0;
  })

}
}
