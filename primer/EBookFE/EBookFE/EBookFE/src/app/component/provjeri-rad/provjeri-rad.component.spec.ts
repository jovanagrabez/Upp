import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvjeriRadComponent } from './provjeri-rad.component';

describe('ProvjeriRadComponent', () => {
  let component: ProvjeriRadComponent;
  let fixture: ComponentFixture<ProvjeriRadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProvjeriRadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvjeriRadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
