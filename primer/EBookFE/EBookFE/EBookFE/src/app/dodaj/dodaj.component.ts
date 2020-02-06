import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/users/user.service';
import {ActivatedRoute} from '@angular/router';
import {RepositoryService} from '../services/repository/repository.service';

@Component({
  selector: 'app-dodaj',
  templateUrl: './dodaj.component.html',
  styleUrls: ['./dodaj.component.css']
})
export class DodajComponent implements OnInit {

  private repeated_password = '';
  private categories = [];
  private formFieldsDto = null;
  private formFields = [];
  private choosen_category = -1;
  private processInstance='';
  private enumValues1 = [];
  private enumValues2 = [];

  dropdownSettings = {};
  selectedItems: any;
  selectedItems1: any;



  urednici: any;
  recenzenti: any;
  constructor(private  userService: UserService, private route: ActivatedRoute, private  repositoryService: RepositoryService) {
    this.userService.getUrednici().subscribe(res => {this.urednici = res; });
    this.userService.getRec().subscribe(res => {this.recenzenti = res; });

    this.route.params.subscribe(params => {
      this.processInstance = params['processInstanceId']; });
      let x = repositoryService.getTask(this.processInstance);
      x.subscribe(
        res => {
          console.log(res);
          this.formFieldsDto = res;
          this.formFields = res.formFields;
          this.processInstance = res.processInstanceId;
          this.formFields.forEach((field) => {

            if (field.type.name == 'enum' && field.id=='recenzenti') {
              this.enumValues1 = Object.keys(field.type.values);
            }else if (field.type.name == 'enum' && field.id=='urednici') {
              this.enumValues2 = Object.keys(field.type.values);

            }
          });
        },
        err => {
          console.log('Error occured');
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

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      if(property == 'recenzenti') {
        o.push({fieldId : property, fieldValue : '', fieldListValue:value[property]});
      } else if(property == 'urednici') {
        o.push({fieldId : property, fieldValue : '', fieldListValue:value[property]});
      } else {
        o.push({fieldId : property, fieldValue : value[property], fieldListValue:[]});
      }
    }

    console.log(o);

    let x = this.userService.saveUredniciRec(o, this.formFieldsDto.taskId);

    x.subscribe(
      res => {
        console.log(res);

        alert('Kreiranje casopisa uspjesno!');

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

  onItemSelect1(item: any) {
    console.log(item);
  }
  onSelectAll1(items: any) {
    console.log(items);
  }


}
