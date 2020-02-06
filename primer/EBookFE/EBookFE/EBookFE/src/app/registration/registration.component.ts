import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/users/user.service';
import {RepositoryService} from '../services/repository/repository.service';
import {IDropdownSettings} from 'ng-multiselect-dropdown';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private repeated_password = "";
  private categories = [];
  private formFieldsDto = null;
  private formFields = [];
  private choosen_category = -1;
  private processInstance = "";
  private enumValues = [];
  private tasks = [];
  dropdownSettings = {};
  selectedItems: any;

  constructor(private userService:  UserService, private repositoryService: RepositoryService, private router: Router) {
    
    let x = repositoryService.startProcess();

    x.subscribe(
      res => {
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.formFields.forEach( (field) =>{
          
          if( field.type.name =='enum'){
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
  onItemSelect(item: any) {
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log(items);
  }
  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      if(property == 'naucna_oblast'){
        o.push({fieldId : property, fieldValue : '', fieldListValue:value[property]});
      }else{
        if(property == 'recezent' && value[property] == '')
          o.push({fieldId : property, fieldValue : 'false', fieldListValue:[]});
        else
          o.push({fieldId : property, fieldValue : value[property], fieldListValue:[]});
      }
    }
    this.repositoryService.completeTask(this.formFieldsDto.taskId);


    console.log(o);
    this.userService.registerUser(o, this.formFieldsDto.taskId).subscribe(
      res => {
        console.log(res);
        alert("You registered successfully!");

      },
      err => {
        console.log("Error occured");
      }


    );
  }

  getTasks(){
    let x = this.repositoryService.getTasks(this.processInstance);

    x.subscribe(
      res => {
        console.log(res);
        this.tasks = res;
      },
      err => {
        console.log("Error occured");
      }
    );
   }

   claim(taskId){
    let x = this.repositoryService.claimTask(taskId);

    x.subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log("Error occured");
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
        console.log("Error occured");
      }
    );
   }

}
