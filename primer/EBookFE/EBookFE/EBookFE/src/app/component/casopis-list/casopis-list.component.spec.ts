import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CasopisListComponent } from './casopis-list.component';

describe('CasopisListComponent', () => {
  let component: CasopisListComponent;
  let fixture: ComponentFixture<CasopisListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CasopisListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CasopisListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
