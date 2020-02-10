import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CasopisService {

  constructor(private httpClient: HttpClient,  private router: Router) { }

  getAll(): any {
    return this.httpClient.get('api/casopis');
  }

  startProcess(): any {
    return this.httpClient.get('api/casopis/startProcess');
  }

  completeTask(taskId, magazineId): any {
    return this.httpClient.post('api/casopis/complete/'.concat(taskId) + '/'.concat(magazineId), null) as Observable<any>

  }

  getForm(instance) {
    return this.httpClient.get('api/casopis/getForm/'.concat(instance));
  }

  temporaryupload(file: File) {
    return this.httpClient.post<any>('api/temporaryupload', file);
  }

  submitMagazine(paper, taskId) {
    return this.httpClient.post('api/casopis/submit/'.concat(taskId), paper);

  }

  getForm1(taskId) {
    return this.httpClient.get('api/users/task/'.concat(taskId));
  }

  getMagazine(processInstance) {
    return this.httpClient.get('api/casopis/get/'.concat(processInstance));


  }
  completeTask1(taskId, o) {
    return this.httpClient.post('api/casopis/tasks/complete/'.concat(taskId), o);
  }
}
