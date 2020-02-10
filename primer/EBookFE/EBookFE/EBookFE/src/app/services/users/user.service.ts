import { Injectable } from '@angular/core';

import { Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs';
import {Router} from '@angular/router';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient,  private router: Router) { }

  fetchUsers() {
    return this.httpClient.get('http://localhost:8080/user/fetch') as Observable<any>;
  }

  registerUser(user, taskId) {
    return this.httpClient.post('api/welcome/post/'.concat(taskId), user) as Observable<any>;
  }

  getToken(): string {
    const currentUser = JSON.parse(localStorage.getItem('loggedUser'));
    if (currentUser !== null) {
      currentUser.token = currentUser.token.replace(':', '');
    }
    const token = currentUser && currentUser.token;
    return token ? token : '';
  }

  getLoggedUserType() {
    const user = JSON.parse(localStorage.getItem('loggedUser'));
    let userRole;
    if (user === null) {
      userRole = '';
    } else {
      for (const role of user.roles) {
        if (role === 'AUTHOR') {
          userRole = 'AUTHOR';
        } else if (role === 'REVIEWER') {
          userRole = 'REVIEWER';
        } else if (role === 'EDITOR') {
          userRole = 'EDITOR';
        } else {
          userRole = 'USER';
        }
      }
    }
    return userRole;
  }

  isLoggedIn() {
    const user = JSON.parse(localStorage.getItem('loggedUser'));

    if (user === null) {
      return false;
    }
    const expiredDate = new Date(new Date(parseInt(user.exp, 10) * 1000));
    const nowDate = new Date();
    if ((expiredDate.getDate() <= nowDate.getDate()) &&
      (expiredDate.getTime() <= nowDate.getTime())) {
      this.logout();
      return false;
    }
    return true;

  }

  login(user): any {
    return this.httpClient.post('api/auth/login', user, {observe: 'response'}).pipe(map(response => response));
  }


  getUserByUsername(username): any {
    return this.httpClient.get('api/users/'.concat(username));
  }

  getRecenzenti(): any {
    return this.httpClient.get('api/users/recenzenti');
  }

  getRec(): any {
    return this.httpClient.get('api/users/recti');
  }

  getUrednici(): any {
    return this.httpClient.get('api/users/urednici');
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  activateRecenzent(data): Observable<any> {
    return this.httpClient.put('api/welcome/activateRecenzent', data);
  }

  saveMagazine(user, taskId) {
    return this.httpClient.post('api/welcome/postMagazine/'.concat(taskId), user) as Observable<any>;


  }

  saveUredniciRec(user, taskId) {
    return this.httpClient.post('api/welcome/postUrednici/'.concat(taskId), user) as Observable<any>;


  }

  getTasks() {
    return this.httpClient.get('api/users/tasks');

  }

  getTask(taskId) {
    return this.httpClient.get('api/users/task/'.concat(taskId));
  }
}
