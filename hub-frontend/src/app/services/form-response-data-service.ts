import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { FormResponse } from '../models/form-response.model';

@Injectable({
  providedIn: 'root',
})
export class FormResponseDataService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = environment.hubBackendApiUrl;

  public createFormResponse(userFormResponse: FormResponse) {
    return this.http.post(
      `${this.apiUrl}/v1/form-responses`,
      userFormResponse.formResponseDto,
      { observe: 'response' },
    );
  }
}
