import {Component} from '@angular/core';
import {UsersService} from 'src/app/services/users.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {RegisterComponent} from '../register/register.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  // logged = false;
  // username: string;
  // password: string;
  //
  // constructor(public usersService: UsersService, private cookies: CookieService, private router: Router) {}
  //
  // login(): void {
  //   const user = {username: this.username, password: this.password};
  //   this.usersService.login(user).subscribe( data => {
  //     this.usersService.setToken(data.jwt);
  //     this.logged = true;
  //     this.cookies.set('username', this.username);
  //     this.router.navigate(['']).then();
  //   });
  // }
  //
  // register(): void {
  //   const user = {username: this.username, password: this.password};
  //   this.usersService.register(user).subscribe();
  // }

  form: FormGroup;

  username: FormControl;
  password: FormControl;

  constructor(
    private userService: UsersService,
    private router: Router,
    public dialog: MatDialog) {
    this.username = new FormControl('', [Validators.required]);
    this.password = new FormControl('', [Validators.required]);

    this.form = new FormGroup({
      username: this.username,
      password: this.password
    });
  }

  onSubmit(form: FormGroupDirective): void {
    const user = {username: this.username.value, password: this.password.value};
    this.userService.login(user).subscribe(data => {
      this.userService.setToken(data.jwt);
      form.resetForm();
      this.router.navigate(['']).then();
    }, error => {
      alert('Something went wrong');
    });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(RegisterComponent, {
      width: '40%'
    });
  }
}
