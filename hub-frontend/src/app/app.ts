import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormOverview } from './form-overview/form-overview';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormOverview],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {}
