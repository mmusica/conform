import { Component, inject, OnInit, signal } from '@angular/core';
import { FormDto } from '../models/model';
import { FormDataService } from '../services/form-data-service';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import {
  MatDialog,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { FormRenderer } from '../form-renderer/form-renderer';

@Component({
  selector: 'app-form-overview',
  imports: [MatButtonModule, MatCardModule, MatIconModule, MatDialogModule],
  templateUrl: './form-overview.html',
  styleUrl: './form-overview.scss',
})
export class FormOverview implements OnInit {
  protected forms = signal(new Array<FormDto>());
  private formService: FormDataService = inject(FormDataService);
  private dialog = inject(MatDialog);

  ngOnInit(): void {
    this.fetchForms();
  }

  fetchForms() {
    this.formService.getForms('string').subscribe((it) => this.forms.set(it));
  }

  openDialog(form: FormDto) {
    this.dialog.open(FormRenderer, { data: form });
  }
}
