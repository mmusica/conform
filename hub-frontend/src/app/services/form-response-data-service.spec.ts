import { TestBed } from '@angular/core/testing';

import { FormResponseDataService } from './form-response-data-service';

describe('FormResponseDataService', () => {
  let service: FormResponseDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormResponseDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
