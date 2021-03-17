import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {MyPantriesComponent} from './components/my-pantries/my-pantries.component';
import {RegisterComponent} from './components/register/register.component';
import {NotFoundPageComponent} from './components/not-found-page/not-found-page.component';


const routes: Routes = [
  {
    path: '',
    component: MyPantriesComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: RegisterComponent
  },
  {
    path: '**',
    component: NotFoundPageComponent
  }
];


@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
