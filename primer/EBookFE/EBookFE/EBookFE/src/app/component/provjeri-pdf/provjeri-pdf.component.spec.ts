import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvjeriPdfComponent } from './provjeri-pdf.component';

describe('ProvjeriPdfComponent', () => {
  let component: ProvjeriPdfComponent;
  let fixture: ComponentFixture<ProvjeriPdfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProvjeriPdfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvjeriPdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
