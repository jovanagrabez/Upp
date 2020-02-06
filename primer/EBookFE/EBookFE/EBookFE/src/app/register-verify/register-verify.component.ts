import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RepositoryService} from '../services/repository/repository.service';

@Component({
  selector: 'app-register-verify',
  templateUrl: './register-verify.component.html',
  styleUrls: ['./register-verify.component.css']
})
export class RegisterVerifyComponent implements OnInit {


  id: number;
  processInstanceId: string;
  constructor(private registrationService: RepositoryService, private route: ActivatedRoute) { }

  ngOnInit() {

    console.log("tu saaam");
    this.route.params.subscribe(params => {
      this.id = +params["id"];
      this.processInstanceId = params['processInstanceId'];
      this.registrationService.verify(this.id, this.processInstanceId).subscribe(response => {
        console.log("OK");
      });
    })
  }

}
