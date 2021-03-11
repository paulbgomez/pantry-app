import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { environment } from '../../environments/environment';
import { Pantry, ProductWithStock, Product } from '../common/interfaces';

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
  getAllPantries(): Observable<Pantry[]>{
    return this.http.get<Pantry[]>(environment.host + '/pantry/all', {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
        })
    });
  }

   getProductsFromPantry(id: number): Observable<ProductWithStock[]>{
    return this.http.get<ProductWithStock[]>(environment.host + '/pantry/all/products/' + id, {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
        })
    });
  }

  getStockProduct(productId: number, pantryId: number): Observable<number>{
    return this.http.get<number>(environment.host + '/pantry/' + pantryId + '/stock/product/' + productId, {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    });
  }

  addPantry(): Observable<any>{

    return this.http.post(environment.host + '/pantry', {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
        })
    });
  }

  updateStock(pantryId: number, productId: number, quantity: number): Observable<any>{
    return this.http.patch(environment.host + '/pantry/' + pantryId + '/' + productId + '/' + quantity, {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    });
  }

  getProducts(): Observable<any>{
    return this.http.get(environment.host + '/product', {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    });
  }


}
