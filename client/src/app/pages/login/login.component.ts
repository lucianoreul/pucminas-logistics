import { Component, OnInit } from '@angular/core';
import { AppLogin } from '../../core/model/app-login.model';
import { LoginService } from '../../core/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLogin: AppLogin = new AppLogin();

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  login() {
    this.loginService.login(this.userLogin).subscribe({
      next: this.handleSuccessLogin.bind(this),
      error: this.handleErrorLogin.bind(this)
    });
  }

  handleSuccessLogin(data: any) {
    console.log(data)
  }

  handleErrorLogin(err: any) {
    console.log(err)
  }

}
