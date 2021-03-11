import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/services/users.service';
import {CookieService} from 'ngx-cookie-service';
import {Router} from '@angular/router';
import {Pantry, Product} from '../../common/interfaces';


@Component({
  selector: 'app-my-pantries',
  templateUrl: './my-pantries.component.html',
  styleUrls: ['./my-pantries.component.css']
})
export class MyPantriesComponent implements OnInit {

  pantries = false;
  pantry: Pantry;
  pantryArray: Pantry[] = [];
  productsDB: Product[] = [];

  constructor(public usersService: UsersService, private cookies: CookieService, private router: Router) { }

  ngOnInit(): void {
   this.checkIsUserLogged();
  }

  checkIsUserLogged(): void {
    if (!this.cookies.check('token')) {
      this.router.navigate(['login']).then();
    } else {
      this.getAllPantries();
      this.getAllProductsFromDB();
    }
  }

  getAllPantries(): void{
    this.pantries = true;
    this.usersService.getAllPantries().subscribe(ListOfPantries => {
      ListOfPantries.forEach(pantry => {
        this.usersService.getProductsFromPantry(pantry.id).subscribe( ListOfProducts => {
          ListOfProducts.forEach( product => {
            this.usersService.getStockProduct(product.id, pantry.id).subscribe(stock => {
              product.stock = stock;
            });
          });
          pantry.ListOfProducts = ListOfProducts;
        });
      });
      this.pantryArray = ListOfPantries;
    });
  }

  logout(): void{
    this.usersService.deleteCookies();
    this.router.navigate(['login']).then();
  }

  updateStock(pantryId: number, productId: number, stock: number): void{
    this.usersService.updateStock(pantryId, productId, stock).subscribe();
  }

  newProduct(pantryId: number): void{
    const nameProduct = document.getElementById('newNamePantry_' + pantryId);
    const stockProduct = document.getElementById('newStockPantry_' + pantryId);
  }

  getAllProductsFromDB(): void{
    this.usersService.getProducts().subscribe(ListOfProducts => {
      this.productsDB = ListOfProducts;
    });
  }

}
