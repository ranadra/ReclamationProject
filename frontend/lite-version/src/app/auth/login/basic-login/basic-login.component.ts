import { Router } from '@angular/router';
import { AuthService } from './../../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-basic-login',
  templateUrl: './basic-login.component.html',
  styleUrls: ['./basic-login.component.scss']
})
export class BasicLoginComponent implements OnInit {
  public mode: number = 0;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() : void{
  }
  public onLogin(formData) {
    this.authService.login(formData).subscribe(
      (resp) => {
        const jwt = resp.headers.get("authorization");
        this.authService.saveToken(jwt);
        this.authService.saveUser(formData);
        console.log(this.authService.getCurrentUser());
       console.log(jwt);
        this.router.navigateByUrl('index');
 },
   (err) => {
     this.mode = 1;
   },
    );
}

}
