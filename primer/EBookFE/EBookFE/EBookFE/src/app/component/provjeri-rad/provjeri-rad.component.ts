import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CasopisService} from '../../services/casopis.service';
import {RepositoryService} from '../../services/repository/repository.service';

@Component({
  selector: 'app-provjeri-rad',
  templateUrl: './provjeri-rad.component.html',
  styleUrls: ['./provjeri-rad.component.css']
})
export class ProvjeriRadComponent implements OnInit {

  taskId: any;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = '';
  private enumValues = [];
  magazine = {naslov: 'naslov', apstrakt: 'apstrakt', kljucniPojmovi: 'pojmovi'}
  nesto = false;

  constructor(private router: Router, private route: ActivatedRoute, private magazineService: CasopisService, private taskService: RepositoryService) {
    this.route.params.subscribe(params => {
      this.taskId = params['taskId'];
    });
    let x = this.magazineService.getForm1(this.taskId);

    x.subscribe(
      res => {
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res['formFields'];
        this.processInstance = res['processInstanceId'];
        this.magazineService.getMagazine(this.processInstance).subscribe(result => {
          // @ts-ignore
            this.magazine = result;
        });
            this.formFields.forEach((field) => {

              if (field.type.name === 'enum') {
                // @ts-ignore
                this.enumValues = Object.keys(field.type.values);
              }
              if (field.id === 'naslov') {
                field.value = this.magazine.naslov;
              }
              if (field.id === 'apstrakt') {
                field.value = this.magazine.apstrakt;
              }
              if (field.id === 'kljucniPojmovi') {
                field.value = this.magazine.kljucniPojmovi;
              }
              if (field.id === 'prihvatljiv') {
                field.value = this.nesto;
              }
            });
          },
          err => {
            console.log('Error occured');
          }
        );
     // });
  }

  ngOnInit() {

  }


  onSubmit(value, f) {

    let o = new Array();
    for (var property in value) {
          o.push({fieldId : property, fieldValue : value[property], fieldListValue:[]});
    }
    this.magazineService.completeTask1(this.taskId, o).subscribe(res => {
      this.router.navigate(['']);
    });

  }
}
