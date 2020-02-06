import { Injectable } from '@angular/core';

import { Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RepositoryService {

  categories = [];
  languages = [];
  books = [];

  constructor(private httpClient: HttpClient, private http: Http) {
  }


  getTaskForUser(username){
    return this.httpClient.get('api/welcome/tasks/'.concat(username));
  }

  startProcess() {
    return this.httpClient.get('api/welcome/get') as Observable<any>
  }
  startMagazineProcess() {
    return this.httpClient.get('api/welcome/getMagazineForm') as Observable<any>
  }
  getTasks(processInstance: string) {

    return this.httpClient.get('api/welcome/get/tasks/'.concat(processInstance)) as Observable<any>
  }

  getTask(processInstance) {
    return this.httpClient.get('api/welcome/getUredniciRecenzenti/'.concat(processInstance)) as Observable<any>;
  }
  claimTask(taskId) {
    return this.httpClient.post('http://localhost:8080/welcome/tasks/claim/'.concat(taskId), null) as Observable<any>
  }

  completeTask(taskId) {
    return this.httpClient.post('api/welcome/tasks/complete/'.concat(taskId), null) as Observable<any>
  }

  verify(id, processInstanceId) {
    return this.httpClient.put('api/welcome/verify/'.concat(id) + '/'.concat(processInstanceId), {});
  }
}
