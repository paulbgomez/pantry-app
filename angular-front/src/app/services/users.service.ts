import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  constructor(private http: HttpClient, private cookies: CookieService) {}

  login(user: {username: string, password: string}): Observable<any> {
    return this.http.post('http://localhost:8080/login', user);
  }

  register(user: {username: string, password: string}): Observable<any> {
    return this.http.post('http://localhost:8080/signup', user, {responseType: 'text'});
  }

  setToken(token: string): void {
    this.cookies.set('token', token);
  }

  getToken(): string {
    return this.cookies.get('token');
  }

  deleteToken(): void {
    this.cookies.delete('token');
  }
}
