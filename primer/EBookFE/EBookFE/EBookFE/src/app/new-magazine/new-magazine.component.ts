import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/users/user.service';
import {RepositoryService} from '../services/repository/repository.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-magazine',
  templateUrl: './new-magazine.component.html',
  styleUrls: ['./new-magazine.component.css']
})
export class NewMagazineComponent implements OnInit {
  private repeated_password = '';
  private categories = [];
  private formFieldsDto = null;
  private formFields = [];
  private choosen_category = -1;
  private processInstance = '';
  private enumValues = [];
  private tasks = [];
  dropdownSettings = {};
  selectedItems: any;
  userTemp: any;

  userRole: any;
  user: any;

  constructor(private userService: UserService, private repositoryService: RepositoryService, private router: Router) {
    let x = repositoryService.startMagazineProcess();
    x.subscribe(
      res => {
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.formFields.forEach( (field) =>{

          if( field.type.name=='enum'){
            this.enumValues = Object.keys(field.type.values);
          }
        });
      },
      err => {
        console.log("Error occured");
      }
    );
  }

  ngOnInit() {

    this.userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    if (this.userTemp !== null) {
      this.userService.getUserByUsername(this.userTemp.sub).subscribe(user => {
        this.user = user;
      });
    }




    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
  }
  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      if(property == 'naucna_oblast') {
        o.push({fieldId : property, fieldValue : '', fieldListValue:value[property]});
      } else {
          o.push({fieldId : property, fieldValue : value[property], fieldListValue:[]});
      }
    }

    console.log(o);

    let x = this.userService.saveMagazine(o, this.formFieldsDto.taskId);

    x.subscribe(
      res => {
        console.log(res);

        alert('Kreiranje casopisa uspjesno!');
        this.router.navigate(['dodaj/'.concat(this.processInstance)]);

      },
      err => {
        console.log('Error occured');
      }
    );
  }


  onItemSelect(item: any) {
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log(items);
  }

  getTasks(){
    let x = this.repositoryService.getTasks(this.processInstance);

    x.subscribe(
      res => {
        console.log(res);
        this.tasks = res;
      },
      err => {
        console.log('Error occured');
      }
    );
  }

  claim(taskId) {
    let x = this.repositoryService.claimTask(taskId);

    x.subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log('Error occured');
      }
    );
  }

  complete(taskId){
    let x = this.repositoryService.completeTask(taskId);

    x.subscribe(
      res => {
        console.log(res);
        this.tasks = res;
      },
      err => {
        console.log('Error occured');
      }
    );
  }
}
