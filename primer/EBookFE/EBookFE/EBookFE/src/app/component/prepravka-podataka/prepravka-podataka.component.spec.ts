import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrepravkaPodatakaComponent } from './prepravka-podataka.component';

describe('PrepravkaPodatakaComponent', () => {
  let component: PrepravkaPodatakaComponent;
  let fixture: ComponentFixture<PrepravkaPodatakaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrepravkaPodatakaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrepravkaPodatakaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
