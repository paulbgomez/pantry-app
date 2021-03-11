import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CookieService} from 'ngx-cookie-service';
import {environment} from '../../environments/environment';
import {Pantry, ProductWithStock} from '../common/interfaces';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  constructor(private http: HttpClient, private cookies: CookieService) {}

  /*
  LOG IN METHODS
   */
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

  /**
  @GET ENDPOINTS
   **/

  getAllPantries(): Observable<Pantry[]>{
    return this.http.get<Pantry[]>(environment.host + '/pantry/all', {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
        })
    });
  }

   getProductsFromPantry(id: number): Observable<ProductWithStock[]>{
    return this.http.get<ProductWithStock[]>(environment.host + `/pantry/all/products/${id}` , {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
        })
    });
  }

  getStockProduct(productId: number, pantryId: number): Observable<number>{
    return this.http.get<number>(environment.host + `/pantry/${pantryId}/stock/product/${productId}`, {headers: new HttpHeaders({
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

  /**
   * @POST ENDPOINTS
   **/

  addPantry(): Observable<any>{
    return this.http.post(environment.host + '/pantry', null,
      {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
        })
    });
  }

  addProductToPantry(pantryId: number, productId: number): Observable<any>{
    return this.http.post<any>(environment.host + `/pantry/add/${pantryId}/${productId}`, null,
      {headers: new HttpHeaders({
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + this.getToken()
        })
      });
  }

  /**
   * @PATCH ENDPOINTS
   **/

  updateStock(pantryId: number, productId: number, quantity: number): Observable<any>{
    return this.http.patch<any>(environment.host + `/pantry/${pantryId}/${productId}/${quantity}`,
      null,
      {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    });
  }

}
