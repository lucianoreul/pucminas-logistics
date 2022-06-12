import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpErrorResponse, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, throwError  } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class ErrorHandlerInterceptor implements HttpInterceptor {

  // this method intercept all request if return error
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(tap({
      error: (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (!(err.status === 401 && (err.message === '' || (err.url && err.url.includes('api/account'))))) {
            return throwError(`Error Code: ${err.status},  Message: ${err.message}`);
          }
        }
      }
    }));
  }
}
