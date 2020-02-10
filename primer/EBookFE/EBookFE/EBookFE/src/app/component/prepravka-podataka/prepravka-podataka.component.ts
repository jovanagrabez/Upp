import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CasopisService} from '../../services/casopis.service';
import {Autor} from '../../modeli/Autor';
import {Paper} from '../../modeli/Paper';

@Component({
  selector: 'app-prepravka-podataka',
  templateUrl: './prepravka-podataka.component.html',
  styleUrls: ['./prepravka-podataka.component.css']
})
export class PrepravkaPodatakaComponent implements OnInit {
  taskId: any;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = '';
  private enumValues = [];
  magazine = {naslov: '', apstrakt: '', kljucniPojmovi: '', pdf: '', komentar: ''}
  nesto = false;
  dropdownSettings = {};
  selectedItems: any;

  autori: Autor[] = [];
  firstname: string = '';
  lastname: string = '';
  email:string = '';
  city:string = '';
  state:string = '';
  rad: any = '';
  radic: any;
  paper: Paper = new Paper();
  naslovRada: string = '';
  apstrakt: string = '';
  kljucniPojmovi: string = '';
  naucnaOblast: any;
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
        this.magazineService.getPrepravkaRada(this.processInstance).subscribe(result => {
            // @ts-ignore
            this.magazine = result;

            this.formFields.forEach((field) => {

              if (field.type.name === 'enum') {
                // @ts-ignore
                this.enumValues = Object.keys(field.type.values);
              }

              if (field.id === 'naslov') {
                field.value = this.magazine.naslov;
              }
              if (field.id === 'komentar') {
                field.value = this.magazine.komentar;
              }
              if (field.id === 'pdf') {
                field.value = this.magazine.pdf;
              }
              if (field.id === 'apstrakt') {
                field.value = this.magazine.apstrakt;
              }
              if (field.id === 'kljucniPojmovi') {
                field.value = this.magazine.kljucniPojmovi;
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

  onSubmit(value: any, f) {

    let o = new Array();
    for (var property in value) {
      if (property === 'naucnaOblasti') {
        this.naucnaOblast = value[property];
        //     o.push({fieldId: property, fieldValue: '', fieldListValue: value[property]});
      } else if (property === 'naslov') {
        this.naslovRada = value[property];
      } else if (property === 'kljucniPojmovi') {
        this.kljucniPojmovi = value[property];
      } else if (property === 'apstrakt') {
        this.apstrakt = value[property];
      }
    }
    this.paper.naslovRada = this.naslovRada;
    this.paper.apstrakt = this.apstrakt;
    this.paper.autori = this.autori;
    this.paper.rad = this.radic;
    this.paper.kljucniPojmovi = this.kljucniPojmovi;
    this.paper.naucnaOblast = this.naucnaOblast;
    this.paper.filename = this.rad;

    this.magazineService.submitMagazine(this.paper, this.formFieldsDto.taskId).subscribe(
      res => {
        alert('Uspjesno poslat magazin!');
        this.router.navigate(['']);

      },
      err => {
        alert('Desila se greska');
        this.router.navigate(['']);

      }


    );

  }
}
