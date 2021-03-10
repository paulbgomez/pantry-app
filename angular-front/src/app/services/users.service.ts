import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  constructor(private http: HttpClient, private cookies: CookieService) {}

  login(user: {username: string, password: string}): Observable<any> {
    return this.http.post(environment.host + '/auth/signin', user);
  }

  register(user: {username: string, password: string}): Observable<any> {
    return this.http.post(environment.host + '/auth/signup', user, {responseType: 'text'});
  }

  setToken(token: string): void {
    this.cookies.set('token', token);
  }

  getToken(): string {
    return this.cookies.get('token');
  }

  deleteCookies(): void {
    this.cookies.delete('token');
    this.cookies.delete('username');
  }

  getAllPantries(): Observable<any>{
    // Definir la cabecera con el username de la cookie
    // const headers = this.headers.set('username', this.cookies.get('username'));
    return this.http.get(environment.host + '/pantry/all');
  }
}
