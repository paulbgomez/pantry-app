import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {CookieService} from 'ngx-cookie-service';
import {environment} from '../../environments/environment';
import {Email, Pantry, Product, ProductWithStock} from '../common/interfaces';
import {catchError} from 'rxjs/operators';
import {AuthInterceptorService} from './auth-interceptor.service';

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
    this.cookies.set('authenticated', 'true');
  }

  getToken(): string {
    return this.cookies.get('token');
  }

  deleteCookies(): void {
    this.cookies.delete('token');
    this.cookies.delete('username');
  }

  authenticated(): boolean {
    return this.cookies.get('authenticated') === 'true';
  }

  sendEmail(email: Email): void{
    this.http.post('http://localhost:8089/email/send', email);
  }

  /**
  @GET ENDPOINTS
   **/

  getPantryById(id: number): Observable<Pantry>{
    return this.http.get<Pantry>(environment.host + `/pantry/${id}`)
      .pipe(
        catchError(this.handleError<Pantry>('getPantryById', null))
      );
  }

  getAllPantries(): Observable<Pantry[]>{
    return this.http.get<Pantry[]>(environment.host + '/pantry/all')
      .pipe(
        catchError(this.handleError<Pantry[]>('getAllPantries', []))
      );
  }

   getProductsFromPantry(id: number): Observable<ProductWithStock[]>{
    return this.http.get<ProductWithStock[]>(environment.host + `/pantry/all/products/${id}`)
      .pipe(
        catchError(this.handleError<ProductWithStock[]>('getProductsFromPantry', []))
      );
  }

  getStockProduct(productId: number, pantryId: number): Observable<number>{
    return this.http.get<number>(environment.host + `/pantry/${pantryId}/stock/product/${productId}`)
      .pipe(
        catchError(this.handleError<number>('getStockProduct', 0))
      );
  }

  getProducts(): Observable<any>{
    return this.http.get(environment.host + '/product');
  }

  findProduct(term: string): Observable<Product[]>{
    if (!term.trim()){
      return of([]);
    }
    return this.http.get<Product[]>(environment.host + `/product/name=${term}`).pipe(
      catchError(this.handleError<Product[]>('findProducts', []))
    );
  }

  /**
   * @POST ENDPOINTS
   **/

  addPantry(): Observable<any>{
    return this.http.post<any>(environment.host + '/pantry', null);
  }

  addProductToPantry(pantryId: number, productId: number): Observable<any>{
    return this.http.post<any>(environment.host + `/pantry/add/${pantryId}/${productId}`, null);
  }

  /**
   * @PATCH ENDPOINTS
   **/

  updateStock(pantryId: number, productId: number, quantity: number): Observable<any>{
    return this.http.patch<any>(environment.host + `/pantry/${pantryId}/${productId}/${quantity}`,
      null);
  }

  editPantryName(pantryId: number, newName: string): Observable<any>{
    return this.http.patch<any>(environment.host +  `/new/pantry/name/${pantryId}/${newName}`,
      null);
  }

  /**
   * @DELETE ENDPOINTS
   **/

  deletePantry(id: number): Observable<any>{
    return this.http.delete<any>(environment.host + `/pantry/${id}`);
  }

  deleteProductFromPantry(pantryId: number, productId: number): Observable<any>{
    return this.http.delete<any>(environment.host + `/pantry/${pantryId}/product/${productId}`);
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
