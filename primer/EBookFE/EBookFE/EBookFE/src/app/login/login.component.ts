import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../services/users/user.service';
import {JwtHelperService} from '@auth0/angular-jwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: any;
  token: any;
  wrongUsernameOrPass: boolean;
  jwtHelper: any;

  constructor( private router: Router, private userService: UserService) {
    this.jwtHelper = new JwtHelperService();
  }

  ngOnInit() {
    this.user = {username: '', password: ''};
    this.token = {accessToken: '', expiresIn: ''};

  }

  login() {
    this.wrongUsernameOrPass = false;
    const headers = new Headers();
    this.userService.login(this.user).subscribe(value => {
      headers.append('Authorization', value.headers.get('Authorization'));
      const userFromToken = this.jwtHelper.decodeToken(headers.get('Authorization'));
      userFromToken.token = headers.get('Authorization');

      localStorage.setItem('loggedUser', JSON.stringify(userFromToken));
      console.log(userFromToken.token + 'AAAAAAAAAAAAAAAAAA');

      location.reload();
      this.router.navigate(['/addNewMagazine']);
    }, error2 => {
      this.wrongUsernameOrPass = true;
    });

  }

}
