import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {UsersService} from '../../services/users.service';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;

  username: FormControl;
  password: FormControl;
  email: FormControl;
  name: FormControl;

  constructor(
    private userService: UsersService,
    private router: Router,
    public dialog: MatDialog) {
    this.username = new FormControl('', [Validators.required]);
    this.password = new FormControl('', [Validators.required]);
    this.name = new FormControl('', [Validators.required]);
    this.email = new FormControl('', [Validators.required, Validators.email]);

    this.form = new FormGroup({
      username: this.username,
      password: this.password,
      email: this.email,
      name: this.name
    });
  }

  ngOnInit(): void {
  }

  onSubmit(form: FormGroupDirective): void {
    const user = {username: this.username.value, password: this.password.value, name: this.name.value, email: this.email.value};
    this.userService.register(user).subscribe(data => {
      this.userService.setToken(data.jwt);
      form.resetForm();
      this.router.navigate(['login']).then();
    }, error => {
      alert('Something went wrong');
    });
  }

}
