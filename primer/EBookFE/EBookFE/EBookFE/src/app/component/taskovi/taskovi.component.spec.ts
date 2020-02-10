import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskoviComponent } from './taskovi.component';

describe('TaskoviComponent', () => {
  let component: TaskoviComponent;
  let fixture: ComponentFixture<TaskoviComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskoviComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskoviComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
