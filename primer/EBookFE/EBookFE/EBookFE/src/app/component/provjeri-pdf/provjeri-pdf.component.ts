import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CasopisService} from '../../services/casopis.service';

@Component({
  selector: 'app-provjeri-pdf',
  templateUrl: './provjeri-pdf.component.html',
  styleUrls: ['./provjeri-pdf.component.css']
})
export class ProvjeriPdfComponent implements OnInit {


  taskId: any;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = '';
  private enumValues = [];
  magazine = {naslov: '', apstrakt: '', kljucniPojmovi: '', filename: ''}
  nesto = false;
  constructor(private route: ActivatedRoute, private magazineService: CasopisService, private router: Router) {
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

            this.formFields.forEach((field) => {

              if (field.type.name === 'enum') {
                // @ts-ignore
                this.enumValues = Object.keys(field.type.values);
              }

              if (field.id === 'pdf') {
                field.value = this.magazine.filename;
              }
              if (field.id === 'komentar') {
                field.value = '';
              }
            });
          },
          err => {
            console.log('Error occured');
          }
        );
      });
  }

  ngOnInit() {
  }

  onSubmit(value, f) {
    let o = new Array();
    for (var property in value) {
      o.push({fieldId : property, fieldValue : value[property], fieldListValue:[]});
    }
    this.magazineService.completeTask1(this.taskId, o).subscribe(res => {
      this.router.navigate(['/taskovi']);
    });

  }
}
