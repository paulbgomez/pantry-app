import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UsersService} from './users.service';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor{

  constructor(private usersService: UsersService){}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.usersService.authenticated()) {
      const authReq = req.clone({
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.usersService.getToken()
        })
      });

      return next.handle(authReq);
    } else {
      return next.handle(req);
    }
  }
}
