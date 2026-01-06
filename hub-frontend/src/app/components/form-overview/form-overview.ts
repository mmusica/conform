import { Component, inject, OnInit, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { FormRenderer } from '../form-renderer/form-renderer';
import { FormDataService } from '../../services/form-data-service';
import { FormDto, ObjectId } from '../../models/model';
import { environment } from '../../../environments/environment';
import { Clipboard } from '@angular/cdk/clipboard';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSnackBar } from '@angular/material/snack-bar';

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

  generateFormUrl(id: ObjectId): void {
    this.clipboard.copy(`${environment.selfUrl}/forms/${id}`);
    this.snackBar.open('URL copied!', 'Dismiss', {
      duration: 5000,
    });
  }
}
