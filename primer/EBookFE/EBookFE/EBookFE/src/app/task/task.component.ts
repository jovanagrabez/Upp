import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/users/user.service';
import {RepositoryService} from '../services/repository/repository.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {


  user: any;
  tasks: any;
  activateRecenzentData = {userId: 0, activate: false}
  constructor(private userService: UserService, private repositoryService: RepositoryService) {

  }

  ngOnInit() {

    this.userService.getRecenzenti().subscribe(res => {this.tasks = res; });
    }

  activateRecenzent(userId: number) {
    this.activateRecenzentData.userId = userId;
    this.activateRecenzentData.activate = true;
    this.userService.activateRecenzent(this.activateRecenzentData).subscribe(
      data => {
        alert('Uspjesno aktiviran!');
      }, error => {
        alert('Eror');

      }
    );
  }

  noActivate(userId: number) {
    this.activateRecenzentData.userId = userId;
    this.activateRecenzentData.activate = false;
    this.userService.activateRecenzent(this.activateRecenzentData).subscribe(
      data => {
        alert('Nije aktiviran!');
      }, error => {
        alert('Greka priliko odbijanja recenzenta');
      }
    );  }

}
