import { Component, inject, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import {
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatOption } from '@angular/material/autocomplete';
import { MatButton } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogActions } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelect } from '@angular/material/select';
import { ActivatedRoute } from '@angular/router';
import { FormComponent, FormDto } from '../../models/model';
import { FormResponseDataService } from '../../services/form-response-data-service';
import { FormResponse } from '../../models/form-response.model';

interface DynamicForm {
  [key: string]: FormControl<string | null>;
}

interface Form {
  components: FormGroup<DynamicForm>;
}

interface FormSubmitResponse {
  formId: string;
  user: string;
  userFormResponse: { [key: string]: any };
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

  private dialogData: FormDto = inject(MAT_DIALOG_DATA, { optional: true });
  private routerData = toSignal(this.activatedRoute.data);

  private form = signal({} as FormDto);
  private fb = inject(NonNullableFormBuilder);
  private formResponseDataService: FormResponseDataService = inject(
    FormResponseDataService,
  );

  formPreview: FormGroup<Form> = this.fb.group({} as Form);
  formComponents: FormComponent[] = [];

  constructor() {
    if (this.dialogData) {
      this.setupForm(this.dialogData);
    } else {
      this.setupForm(this.routerData()?.['form']);
    }
  }

  onSubmit() {
    this.formResponseDataService
      .createFormResponse(new FormResponse(this.createFormSubmitResponse()))
      .subscribe((res) => console.log(res.status));
  }

  private createFormSubmitResponse(): FormSubmitResponse {
    return {
      formId: this.form().id,
      user: 'string',
      userFormResponse: this.formPreview.value.components as {
        [key: string]: any;
      },
    };
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
      result[it.name] = new FormControl('', Validators.required);
    });
    return result;
  }

  isFormValid(): boolean {
    return this.formPreview.status === 'VALID';
  }

  getValidationErrorMessage(name: string): string {
    let invalid =
      this.formPreview.controls.components.controls[name].hasError('required');
    if (invalid) {
      return 'This field is required';
    }
    return '';
  }
}
