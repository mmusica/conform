import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, tap } from 'rxjs';

export interface Config {
  keycloak: {
    config: {
      url: string;
      realm: string;
      clientId: string;
    },
    initOptions: {
      onLoad: 'login-required';
    };
  };
}

@Injectable({
  providedIn: 'root',
})
export class ConfigService {
  private config: Config | null = null;
  constructor(private readonly httpClient: HttpClient) {}

  public load(): Promise<Config> {
    return firstValueFrom(
      this.httpClient
        .get<Config>('/assets/config.json')
        .pipe(tap((it) => (this.config = it))),
    );
  }

  get configuration(): Config {
    if (!this.config) {
      throw new Error('Config loading...');
    }
    return this.config;
  }
}
