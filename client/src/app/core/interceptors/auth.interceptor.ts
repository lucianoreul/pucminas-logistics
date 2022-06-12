import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL, SERVER_TOKEN_KEY_NAME } from '../../app.constants';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  // this method intercept all request for implement the token if already exist.
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!req || !req.url || (req.url.startsWith('http') && !(SERVER_API_URL && req.url.startsWith(SERVER_API_URL)))) {
      return next.handle(req);
    }
    let idToken;
    try {
      idToken = localStorage.getItem(SERVER_TOKEN_KEY_NAME) || sessionStorage.getItem(SERVER_TOKEN_KEY_NAME)
    } catch (error) {
      console.log(error);
    }

    if (idToken) {
      const cloned = req.clone({
        headers: req.headers.set('Authorization', idToken)
      });
      return next.handle(cloned);
    } else {
      return next.handle(req);
    }
  }

}
