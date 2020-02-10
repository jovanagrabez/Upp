import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/users/user.service';
import {CasopisService} from '../../services/casopis.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-izbor-recenzenata',
  templateUrl: './izbor-recenzenata.component.html',
  styleUrls: ['./izbor-recenzenata.component.css']
})
export class IzborRecenzenataComponent implements OnInit {

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
  taskId: any;
  constructor(private router: Router, private userService: UserService, private magazineService: CasopisService, private  route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.taskId = params['taskId'];
      console.log(this.taskId);
    });
    let x = this.magazineService.getForm2(this.taskId);

    x.subscribe(
      res => {
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res['formFields'];
        this.processInstance = res['processInstanceId'];
        this.formFields.forEach((field) => {

          if (field.type.name === 'enum') {
            // @ts-ignore
            this.enumValues = Object.keys(field.type.values);
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

  onItemSelect(item: any) {
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log(items);
  }

  onSubmit(value: any) {
    let o = new Array();
    for (var property in value) {
      if(property === 'reviewers'){
        o.push({fieldId : property, fieldValue : '', fieldListValue: value[property]});
      } else
          o.push({fieldId : property, fieldValue : value[property], fieldListValue:[]});
    }
    this.magazineService.completeTask1(this.taskId, o).subscribe(res => {
      this.router.navigate(['/taskovi']);
    });

  }
}
