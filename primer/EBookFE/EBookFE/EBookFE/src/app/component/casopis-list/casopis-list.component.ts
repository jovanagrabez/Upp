import { Component, OnInit } from '@angular/core';
import {CasopisService} from '../../services/casopis.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-casopis-list',
  templateUrl: './casopis-list.component.html',
  styleUrls: ['./casopis-list.component.css']
})
export class CasopisListComponent implements OnInit {

  magazinesList: any;
  taskId: any;
  processInstanceId = '';
  constructor(private casopisService: CasopisService, private router: Router) {


  }

  ngOnInit() {
    this.casopisService.getAll().subscribe(magazines => {
      this.magazinesList = magazines.casopisi;
      this.taskId = magazines.taskId;
      this.processInstanceId = magazines.processInstanceId;
    });
    console.log('taskId' + this.processInstanceId);
  }

  dodajRad(magazineId: any) {
   this.casopisService.completeTask(this.taskId, magazineId).subscribe(res => {
     this.router.navigate(['/dodajRad', this.processInstanceId]);
   });
  }
}
