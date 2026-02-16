import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { FormDto } from '../models/model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FormDataService {
  private readonly http: HttpClient = inject(HttpClient);
  private readonly apiUrl: string = environment.hubBackendApiUrl;

  public getFormsByUsername(user: string): Observable<Array<FormDto>> {
    return this.http.get<Array<FormDto>>(`${this.apiUrl}/v1/forms`, {
      params: { user: user },
    });
  }

  public getFormById(id: string): Observable<FormDto> {
    return this.http.get<FormDto>(`${this.apiUrl}/v1/forms/${id}`);
  }
}
