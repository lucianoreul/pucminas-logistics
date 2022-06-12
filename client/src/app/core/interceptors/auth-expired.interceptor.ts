import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { LoginService } from '../services/login.service';

@Injectable()
export class AuthExpiredInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private loginService: LoginService,
    private toastr: ToastrService
  ) {}

  // this method intercept all request and if is authentication request
  // and return expired error, logout and return to login route.
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(tap({
      error: (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401
            && err.url
            && !err.url.includes('/api/authenticate')
          ) {
            this.loginService.logout();
            this.router.navigate(['/']);
            this.toastr.warning('Sessão expirada');
          }
        }
      }
    }));
  }
}
