import {Component, OnInit, Output} from '@angular/core';
import {speedDialFabAnimations} from '../../speed-dial-fab.animations';
import {UsersService} from '../../services/users.service';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {DialogAddPantryComponent} from '../dialog-add-pantry/dialog-add-pantry.component';
import {Pantry} from '../../common/interfaces';
import {MyPantriesComponent} from '../my-pantries/my-pantries.component';
import * as EventEmitter from 'events';

@Component({
  selector: 'app-floating-button',
  templateUrl: './floating-button.component.html',
  styleUrls: ['./floating-button.component.scss'],
  animations: speedDialFabAnimations

})
export class FloatingButtonComponent implements OnInit {

  fabButtons = [
    {
      icon: 'person_off'
    },
    {
      icon: 'alternate_email'
    },
    {
      icon: 'playlist_add'
    }
  ];

  buttons = [];
  fabTogglerState = 'inactive';
  pantryArray: Pantry[] = [];

  constructor(public usersService: UsersService, private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  showItems(): void {
    this.fabTogglerState = 'active';
    this.buttons = this.fabButtons;
  }
  hideItems(): void {
    this.fabTogglerState = 'inactive';
    this.buttons = [];
  }
  onToggleFab(): void {
    this.buttons.length ? this.hideItems() : this.showItems();
  }

  difFunctionalities(i: number): void {
    switch (i){
      case 0:
        this.logout();
        break;
      case 1:
        console.log('send email');
        break;
      case 2:
        this.openDialog();
        break;
    }
  }

  logout(): void{
      this.usersService.deleteCookies();
      this.router.navigate(['login']).then();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogAddPantryComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === true){
        this.addPantry();
      }
    });
  }

  addPantry(): void{
      this.usersService.addPantry().subscribe(pantry => {
        this.usersService.getAllPantries().subscribe(listOfPantries => {
          this.pantryArray = listOfPantries;
        });
        this.pantryArray.push(pantry);
      });
  }

}
