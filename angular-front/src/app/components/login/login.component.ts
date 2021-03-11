import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/services/users.service';
import { CookieService } from 'ngx-cookie-service';
import {Pantry} from '../../common/interfaces';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  logged = false;
  username: string;
  password: string;
  pantries = false;
  pantry: Pantry;
  pantryArray: Pantry[] = [];
  pantryArrayFormatted: [] = [];

  constructor(public usersService: UsersService, private cookies: CookieService) {}

  login(): void {
    const user = {username: this.username, password: this.password};
    this.usersService.login(user).subscribe( data => {
      this.usersService.setToken(data.jwt);
      this.logged = true;
      this.cookies.set('username', this.username);
    });
  }

  register(): void {
    const user = {username: this.username, password: this.password};
    this.usersService.register(user).subscribe();
  }

  logout(): void{
    this.usersService.deleteCookies();
    this.logged = false;
  }

  getAllPantries(): void{
    this.pantries = true;
    this.usersService.getAllPantries().subscribe(e => {
      this.pantryArray.push(e);
    });
    for (let i = 0; i < this.pantryArray.length; i++){

    }
    console.log(this.pantryArray);
  }

}
