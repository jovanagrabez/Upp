import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/users/user.service';
import {CasopisService} from '../../services/casopis.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Autor} from '../../modeli/Autor';
import {Paper} from '../../modeli/Paper';

@Component({
  selector: 'app-dodavanje-rada',
  templateUrl: './dodavanje-rada.component.html',
  styleUrls: ['./dodavanje-rada.component.css']
})
export class DodavanjeRadaComponent implements OnInit {

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

  constructor(private userService: UserService, private magazineService: CasopisService, private  route: ActivatedRoute, private router: Router) {

    this.route.params.subscribe(params => {
      this.processInstance = params['processInstanceId'];
    });
    let x = this.magazineService.getForm(this.processInstance);

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
  onSubmit(value, form) {
    let o = new Array();
    for (var property in value) {
      if (property === 'naucnaOblast') {
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
  addAuthor() {
    let aa = new Autor();
    aa.firstname = this.firstname;
    aa.lastname = this.lastname;
    aa.email = this.email;
    aa.city = this.city;
    aa.state = this.state;
    this.autori.push(aa);
    this.firstname = '';
    this.lastname = '';
    this.email = '';
    this.city = '';
    this.state = '';
  }

  uploadFile(event: any) {
    //ovo koristim

    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      let file: File = fileList[0];
   /*   this.magazineService.temporaryupload(file)
        .subscribe(data => {
          this.radic = data.filename;

        });*/
    }
  }

}
