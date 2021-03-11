import {Component} from '@angular/core';
import {UsersService} from 'src/app/services/users.service';
import {CookieService} from 'ngx-cookie-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  logged = false;
  username: string;
  password: string;

  constructor(public usersService: UsersService, private cookies: CookieService, private router: Router) {}

  login(): void {
    const user = {username: this.username, password: this.password};
    this.usersService.login(user).subscribe( data => {
      this.usersService.setToken(data.jwt);
      this.logged = true;
      this.cookies.set('username', this.username);
      this.router.navigate(['']).then();
    });
  }

  register(): void {
    const user = {username: this.username, password: this.password};
    this.usersService.register(user).subscribe();
  }

}
