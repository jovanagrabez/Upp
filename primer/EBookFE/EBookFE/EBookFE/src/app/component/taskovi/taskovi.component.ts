import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/users/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-taskovi',
  templateUrl: './taskovi.component.html',
  styleUrls: ['./taskovi.component.css']
})
export class TaskoviComponent implements OnInit {

  tasks: any[];
  user: any;
  constructor(private userService: UserService, private router: Router) {this.user = JSON.parse(localStorage.getItem('user'));
  }

  ngOnInit() {

    this.userService.getTasks().subscribe((response) => {
      console.log(response);
      // @ts-ignore
      this.tasks = response;
    });
  }

  redirectToTask(taskId, taskDefinitionId) {

    if (taskDefinitionId === 'Pregled rada urednik')
       this.router.navigate(['/provjeriRad', taskId]);



  }

}
