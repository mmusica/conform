import { Clipboard } from '@angular/cdk/clipboard';
import { Component, inject, OnInit, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { environment } from '../../../environments/environment';
import { FormDto } from '../../models/model';
import { FormDataService } from '../../services/form-data-service';
import { FormRenderer } from '../form-renderer/form-renderer';

@Component({
  selector: 'app-form-overview',
  imports: [
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatDialogModule,
    MatTooltipModule,
  ],
  templateUrl: './form-overview.html',
  styleUrl: './form-overview.scss',
})
export class FormOverview implements OnInit {
  protected forms = signal(new Array<FormDto>());
  private formService: FormDataService = inject(FormDataService);
  private dialog = inject(MatDialog);
  private clipboard: Clipboard = inject(Clipboard);
  private snackBar = inject(MatSnackBar);

  ngOnInit(): void {
    this.fetchForms();
  }

  fetchForms() {
    this.formService
      .getFormsByUsername('string')
      .subscribe((it) => this.forms.set(it));
  }

  openFormDialog(form: FormDto) {
    this.dialog.open(FormRenderer, {
      width: '90vw',
      maxWidth: '90vw',
      data: form,
    });
  }

  generateFormUrl(id: String): void {
    this.clipboard.copy(`${environment.selfUrl}/forms/${id}`);
    this.snackBar.open('URL copied!', 'Dismiss', {
      duration: 5000,
    });
  }
}
