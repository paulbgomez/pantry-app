import {Component, OnInit} from '@angular/core';
import {UsersService} from 'src/app/services/users.service';
import {CookieService} from 'ngx-cookie-service';
import {Router} from '@angular/router';
import {Pantry, Product} from '../../common/interfaces';

@Component({
  selector: 'app-my-pantries',
  templateUrl: './my-pantries.component.html',
  styleUrls: ['./my-pantries.component.scss']
})
export class MyPantriesComponent implements OnInit {

  panelOpenState = false;
  pantries = false;
  pantry: Pantry;
  selectedProduct!: Product;
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


  updateStock(pantryId: number, productId: number, stock: number): void{
    this.usersService.updateStock(pantryId, productId, stock).subscribe();
  }

  newProduct(pantryId: number, productId: number): void{
    this.usersService.addProductToPantry(pantryId, productId).subscribe();
  }

  getAllProductsFromDB(): void{
    this.usersService.getProducts().subscribe(ListOfProducts => {
      this.productsDB = ListOfProducts;
      console.log(ListOfProducts);
    });
  }

  selectProduct(product: Product, pantryId: number): void{
    this.usersService.getPantryById(pantryId).subscribe(pantry => {
      this.usersService.addProductToPantry(pantry.id, product.id).subscribe();
    });
    this.selectedProduct = product;
    this.sleep(1500).then(() => { this.usersService.getProductsFromPantry(pantryId).subscribe(); });
  }

  deleteProduct(pantryId: number, productId: number): void{
    this.usersService.deleteProductFromPantry(pantryId, productId).subscribe();
    this.sleep(1500).then(() => { this.usersService.getProductsFromPantry(pantryId).subscribe(); });
  }

  sleep(ms): Promise<any>{
    return new Promise(resolve => setTimeout(resolve, ms));
  }

}
