import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {CookieService} from 'ngx-cookie-service';
import {environment} from '../../environments/environment';
import {Email, Pantry, Product, ProductWithStock} from '../common/interfaces';
import {catchError} from 'rxjs/operators';

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

  register(user: {username: string, password: string, name: string, email: string}): Observable<any> {
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

  sendEmail(email: Email): void{
    this.http.post('http://localhost:8089/email/send', email, {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    });
  }

  /**
  @GET ENDPOINTS
   **/

  getPantryById(id: number): Observable<Pantry>{
    return this.http.get<Pantry>(environment.host + `/pantry/${id}`, {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    });
  }

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

  findProduct(term: string): Observable<Product[]>{
    if (!term.trim()){
      return of([]);
    }
    return this.http.get<Product[]>(environment.host + `/product/name=${term}`, {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    }).pipe(
      catchError(this.handleError<Product[]>('findProducts', []))
    );
  }

  /**
   * @POST ENDPOINTS
   **/

  addPantry(): Observable<any>{
    return this.http.post<any>(environment.host + '/pantry', null,
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

  editPantryName(pantryId: number, newName: string): Observable<any>{
    return this.http.patch<any>(environment.host +  `/new/pantry/name/${pantryId}/${newName}`,
      null,
      {headers: new HttpHeaders({
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + this.getToken()
        })
      });
  }

  /**
   * @DELETE ENDPOINTS
   **/

  deletePantry(id: number): Observable<any>{
    return this.http.delete<any>(environment.host + `/pantry/${id}`, {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    });
  }

  deleteProductFromPantry(pantryId: number, productId: number): Observable<any>{
    return this.http.delete<any>(environment.host + `/pantry/${pantryId}/product/${productId}`, {headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    });
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  // tslint:disable-next-line:typedef
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
