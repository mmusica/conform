import { Component, inject, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import {
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatOption } from '@angular/material/autocomplete';
import { MatButton } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogActions } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelect } from '@angular/material/select';
import { ActivatedRoute } from '@angular/router';
import { FormComponent, FormDto } from '../../models/model';

interface DynamicForm {
  [key: string]: FormControl<string | null>;
}

interface Form {
  components: FormGroup<DynamicForm>;
}

@Component({
  selector: 'app-form-renderer',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatOption,
    MatSelect,
    MatButton,
    MatInputModule,
    MatDialogActions,
  ],
  templateUrl: './form-renderer.html',
  styleUrl: './form-renderer.scss',
})
export class FormRenderer {
  private activatedRoute = inject(ActivatedRoute);

  private dialogData = inject(MAT_DIALOG_DATA, { optional: true });
  private routerData = toSignal(this.activatedRoute.data);

  private form = signal({} as FormDto);
  private fb = inject(NonNullableFormBuilder);

  formPreview: FormGroup<Form> = this.fb.group({} as Form);
  formComponents: FormComponent[] = [];

  constructor() {
    if (this.dialogData) {
      this.form.set(this.dialogData);
      this.setupForm(this.form());
    } else {
      this.setupForm(this.routerData()?.['form']);
    }
  }

  onSubmit() {
    console.log(this.formPreview.value);
  }

  private setupForm(form: FormDto): void {
    this.form.set(form);
    this.formPreview = this.createForm(this.form());
    this.formComponents = this.form().components;
  }

  createForm(form: FormDto): FormGroup<Form> {
    return this.fb.group<Form>({
      components: this.fb.group<DynamicForm>(this.renderFormAsObject(form)),
    });
  }

  renderFormAsObject(form: FormDto) {
    let result: DynamicForm = {};
    form.components?.forEach((it) => {
      result[it.name] = new FormControl('');
    });
    return result;
  }
}
