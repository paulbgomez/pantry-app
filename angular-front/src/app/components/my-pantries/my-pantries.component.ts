import {Component, OnInit } from '@angular/core';
import {UsersService} from 'src/app/services/users.service';
import {CookieService} from 'ngx-cookie-service';
import {Router} from '@angular/router';
import {Pantry, Product} from '../../common/interfaces';
import {MatDialog} from '@angular/material/dialog';
import {DialogContentComponent} from '../dialog-content/dialog-content.component';

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
  newStock: number;

  constructor(public usersService: UsersService, private cookies: CookieService, private router: Router, public dialog: MatDialog) { }

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

  addNewPantry(pantry: Pantry): void{
    this.pantryArray = [...this.pantryArray, pantry];
  }

  openDialog(pantryId: number, productId: number, stock: number): void {
    const dialogRef = this.dialog.open(DialogContentComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === true){
        this.updateStock(pantryId, productId, stock);
      }
    });
  }

  updateStock(pantryId: number, productId: number, stock: number): void{
    this.usersService.updateStock(pantryId, productId, stock).subscribe();
  }

  getAllProductsFromDB(): void {
    this.usersService.getProducts().subscribe(ListOfProducts => {
      this.productsDB = ListOfProducts;
    });
  }

  deletePantry(pantryId: number): void{
    this.usersService.deletePantry(pantryId).subscribe(() => {
      const indexErasedElement = this.pantryArray.findIndex(pantry => pantry.id === pantryId);
      this.pantryArray.splice(indexErasedElement, 1);
    });
  }

  selectProduct(product: Product, pantryId: number): void{
    this.usersService.getPantryById(pantryId).subscribe(() => {
      this.usersService.addProductToPantry(pantryId, product.id).subscribe(() => {
        this.pantryArray.filter(pantry => {
          if (pantry.id === pantryId){
            pantry.ListOfProducts.push(product);
          }
        });
        this.updateOnePantry(pantryId);
      });
    });
    this.selectedProduct = product;
  }

  updateOnePantry(pantryId: number): void {
    this.usersService.getPantryById(pantryId).subscribe(pantry => {
      this.usersService.getProductsFromPantry(pantryId).subscribe(pantryUpdated => {
        pantry.ListOfProducts = pantryUpdated;
      });
    });
  }

  deleteProduct(pantryId: number, productId: number, i: number): void{
    this.usersService.deleteProductFromPantry(pantryId, productId).subscribe(() => {
      this.pantryArray.filter(pantry => {
        if (pantry.id === pantryId){
          pantry.ListOfProducts.splice(i, 1);
        }
      });
      this.updateOnePantry(pantryId);
    });
  }


}
