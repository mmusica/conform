import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { FormDto } from '../models/model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FormDataService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = environment.hubBackendApiUrl;

  public getForms(user: string): Observable<Array<FormDto>> {
    return this.http.get<Array<FormDto>>(`${this.apiUrl}/v1/form/${user}`);
  }
}
