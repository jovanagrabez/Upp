import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RegistrationComponent} from './registration/registration.component';
import {LoginComponent} from './login/login.component';
import {NewMagazineComponent} from './new-magazine/new-magazine.component';
import {RegisterVerifyComponent} from './register-verify/register-verify.component';
import {TaskComponent} from './task/task.component';
import {DodajComponent} from './dodaj/dodaj.component';

const appRoutes: Routes = [

  {path: 'registrate', component : RegistrationComponent},
  {path: 'login', component: LoginComponent},
  {path: 'addNewMagazine', component: NewMagazineComponent},
  {path: 'register/verify/:id/:processInstanceId', component: RegisterVerifyComponent},
  {path: 'tasks', component: TaskComponent},
  {path: 'dodaj/:processInstanceId', component: DodajComponent}



];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(appRoutes, { useHash: true }), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
