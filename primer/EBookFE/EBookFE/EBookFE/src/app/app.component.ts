import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  private user = JSON.parse(localStorage.getItem('user'));
   role = localStorage.getItem('role');

  loggedIn(){
    if(this.user){
      return true; 
    }else{
      return false;
    }
  }

  notLoggedIn(){
    if(!this.user){
      return true; 
    }else{
      return false;
    }
  }

  getLoggedUserType() {
    const user = JSON.parse(localStorage.getItem('loggedUser'));
    let userRole;
    console.log('lalalal');
    if (user === null) {
      userRole = '';
    } else {
      for (const role of user.roles) {
        if (role === 'AUTHOR') {
          userRole = 'AUTHOR';
          console.log('autor' + userRole);
        } else if (role === 'RECENZENT') {
          userRole = 'RECENZENT';
        } else if (role === 'EDITOR') {
          userRole = 'EDITOR';
        } else {
          userRole = 'USER';
        }
      }
    }
    return userRole;
  }

  isAdmin(){
    if(this.role == "AUTHOR"){
      return true; 
    }else{
      return false;
    }
  }
}
