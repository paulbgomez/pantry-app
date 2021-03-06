import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  logged: boolean = false;
  username: string;
  password: string;

  constructor(public usersService: UsersService) {}

  login() {
    const user = {username: this.username, password: this.password};
    this.usersService.login(user).subscribe( data => {
      this.usersService.setToken(data.jwt);
      this.logged = true;
    });
  }

  register() {
    const user = {username: this.username, password: this.password};
    this.usersService.register(user).subscribe();
  }

  logout(){
    this.usersService.deleteToken();
    this.logged = false;
  }
}
