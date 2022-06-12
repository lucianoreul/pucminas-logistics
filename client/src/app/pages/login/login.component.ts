import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { ToastrService } from 'ngx-toastr';
import { AppLogin } from '../../core/model/app-login.model';
import { LoginService } from '../../core/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLogin: AppLogin = new AppLogin();

  constructor(
    private loginService: LoginService,
    private toastService: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  login() {
    this.loginService.login(this.userLogin).subscribe({
      next: this.handleSuccessLogin.bind(this),
      error: this.handleErrorLogin.bind(this)
    });
  }

  handleSuccessLogin(data: any) {
    this.loginService.storeAuthenticationToken(data.body.accessToken, false);
    this.router.navigateByUrl('/home').then();
  }

  handleErrorLogin(err: any) {
    console.log(err)
    this.toastService.error('Falha na Autenticação.');
  }

}
