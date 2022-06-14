import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL, SERVER_TOKEN_KEY_NAME } from '../../app.constants';

@Injectable({ providedIn: 'root' })
export class LoginService {

  private resourceUrl = SERVER_API_URL + '/api/auth';

  constructor(private http: HttpClient) {}

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.resourceUrl}/login`, credentials, { observe: 'response' });
  }

  logout(): Observable<any> {
    return new Observable((observer) => {
      localStorage.removeItem(SERVER_TOKEN_KEY_NAME);
      sessionStorage.removeItem(SERVER_TOKEN_KEY_NAME);
      observer.complete();
    });
  }

  storeAuthenticationToken(jwt: string, rememberMe: any) {
    if (rememberMe) {
      localStorage.setItem(SERVER_TOKEN_KEY_NAME, jwt);
    } else {
      sessionStorage.setItem(SERVER_TOKEN_KEY_NAME, jwt);
    }
  }
}
