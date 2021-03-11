import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { environment } from '../../environments/environment';
import {Product} from '../common/interfaces';

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

  //http interceptor 2 archivos diferenteh
  //auth guard
  getAllPantries(): Observable<any>{
    return this.http.get(environment.host + '/pantry/all', {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
        })
    });
  }

  async getProductsFromPantry(id: number): Promise<Product[]>{
    const data = this.http.get<Product[]>(environment.host + '/pantry/all/products/' + id, {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
        })
    }).toPromise();
    return await data;
  }

  addPantry(): Observable<any>{

    return this.http.post(environment.host + '/pantry', {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
        })
    });
  }


}
