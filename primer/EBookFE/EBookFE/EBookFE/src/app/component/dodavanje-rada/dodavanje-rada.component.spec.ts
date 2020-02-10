import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DodavanjeRadaComponent } from './dodavanje-rada.component';

describe('DodavanjeRadaComponent', () => {
  let component: DodavanjeRadaComponent;
  let fixture: ComponentFixture<DodavanjeRadaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DodavanjeRadaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DodavanjeRadaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
