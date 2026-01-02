import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormOverview } from './form-renderer';

describe('FormRenderer', () => {
  let component: FormOverview;
  let fixture: ComponentFixture<FormOverview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormOverview],
    }).compileComponents();

    fixture = TestBed.createComponent(FormOverview);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
