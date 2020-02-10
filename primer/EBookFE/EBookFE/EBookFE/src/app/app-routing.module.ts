import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RegistrationComponent} from './registration/registration.component';
import {LoginComponent} from './login/login.component';
import {NewMagazineComponent} from './new-magazine/new-magazine.component';
import {RegisterVerifyComponent} from './register-verify/register-verify.component';
import {TaskComponent} from './task/task.component';
import {DodajComponent} from './dodaj/dodaj.component';
import {AppComponent} from './app.component';
import {CasopisListComponent} from './component/casopis-list/casopis-list.component';
import {DodavanjeRadaComponent} from './component/dodavanje-rada/dodavanje-rada.component';
import {TaskoviComponent} from './component/taskovi/taskovi.component';
import {ProvjeriRadComponent} from './component/provjeri-rad/provjeri-rad.component';

const appRoutes: Routes = [

  {path: 'registrate', component : RegistrationComponent},
  {path: 'radovi', component : CasopisListComponent},
  {path: 'login', component: LoginComponent},
  {path: 'addNewMagazine', component: NewMagazineComponent},
  {path: 'register/verify/:id/:processInstanceId', component: RegisterVerifyComponent},
  {path: 'tasks', component: TaskComponent},
  {path: 'taskovi', component: TaskoviComponent},
  {path: 'provjeriRad/:taskId', component: ProvjeriRadComponent},

  {path: 'dodaj/:processInstanceId', component: DodajComponent},
  {path: 'dodajRad/:processInstanceId', component: DodavanjeRadaComponent}



];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(appRoutes, { useHash: true }), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
