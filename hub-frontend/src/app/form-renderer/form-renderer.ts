import { Component, inject, Input, OnInit, signal } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormDto } from '../models/model';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-form-renderer',
  imports: [ReactiveFormsModule],
  templateUrl: './form-renderer.html',
  styleUrl: './form-renderer.scss',
})
export class FormRenderer implements OnInit {
  form: FormDto = inject(MAT_DIALOG_DATA);
  formSignal = signal(this.form);

  ngOnInit(): void {}

  createForm() {}
}
