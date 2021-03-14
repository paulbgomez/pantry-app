import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {UsersService} from '../../services/users.service';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {Email} from '../../common/interfaces';

@Component({
  selector: 'app-email-dialog',
  templateUrl: './email-dialog.component.html',
  styleUrls: ['./email-dialog.component.css']
})
export class EmailDialogComponent implements OnInit {

  form: FormGroup;

  address: FormControl;
  subject: FormControl;
  content: FormControl;

  constructor(
    private userService: UsersService,
    private router: Router,
    public dialog: MatDialog) {
    this.address = new FormControl('', [Validators.required, Validators.email]);
    this.subject = new FormControl('', [Validators.required]);
    this.content = new FormControl('', [Validators.required]);

    this.form = new FormGroup({
      address: this.address,
      subject: this.subject,
      content: this.content,
    });
  }

  ngOnInit(): void {
  }

  onSubmit(form: FormGroupDirective): void {
    const mail: Email = {address: this.address.value, subject: this.subject.value, content: this.content.value};
    this.userService.sendEmail(mail);
    form.resetForm();
  }

}
