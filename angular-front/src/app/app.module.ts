import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {CookieService} from 'ngx-cookie-service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {MyPantriesComponent} from './components/my-pantries/my-pantries.component';
import {AppRoutingModule} from './app-routing.module';
import {RegisterComponent} from './components/register/register.component';
import {MatIconModule} from '@angular/material/icon';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatOptionModule} from '@angular/material/core';
import { MatSelectModule} from '@angular/material/select';
import { FloatingButtonComponent } from './components/floating-button/floating-button.component';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import { DialogContentComponent } from './components/dialog-content/dialog-content.component';
import { DialogAddPantryComponent } from './components/dialog-add-pantry/dialog-add-pantry.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { EmailDialogComponent } from './components/email-dialog/email-dialog.component';
import { ProductSearchComponent } from './components/product-search/product-search.component';
import { NotFoundPageComponent } from './components/not-found-page/not-found-page.component';
import {AuthInterceptorService} from './services/auth-interceptor.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MyPantriesComponent,
    RegisterComponent,
    FloatingButtonComponent,
    DialogContentComponent,
    DialogAddPantryComponent,
    EmailDialogComponent,
    ProductSearchComponent,
    NotFoundPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatIconModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatOptionModule,
    MatSelectModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatAutocompleteModule
  ],
  providers: [CookieService,
    {
    provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
