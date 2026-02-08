import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable } from 'rxjs';
import { FormDataService } from '../../services/form-data-service';
import { FormOverview } from './form-overview';

describe('FormOverview', () => {
  let fixture: ComponentFixture<FormOverview>;
  let formDataServiceMock: { getFormsByUsername: jest.Mock };

  beforeEach(async () => {
    formDataServiceMock = {
      getFormsByUsername: jest.fn().mockReturnValue(
        new Observable((subscriber) => {
          subscriber.next([
            {
              id: 10,
              name: '5',
              user: 'test',
              modifiedAt: new Date(),
              component: [],
            },
          ]);
          subscriber.complete();
        }),
      ),
    };

    TestBed.configureTestingModule({
      providers: [
        {
          provide: FormDataService,
          useValue: formDataServiceMock,
        },
      ],
    });
    fixture = TestBed.createComponent(FormOverview);
  });

  it('Form overview should show forms', async () => {
    fixture.detectChanges();
    await fixture.whenStable();
    const htmlElement: HTMLElement = fixture.nativeElement;
    const formContainer = htmlElement.querySelector('.form-container');
    const title = htmlElement.querySelector('.mat-mdc-card-title')?.textContent;
    expect(formContainer?.children.length).toBe(1);
    expect(title?.trim()).toBe('5');
  });
});
