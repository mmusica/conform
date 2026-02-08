import { FormResponseDto } from './model';

export class FormResponse {
  userFormResponse: { [key: string]: string };
  formId: string;
  user: string;

  constructor(data: Partial<FormResponse>) {
    this.userFormResponse = data.userFormResponse || {};
    this.formId = data.formId || '';
    this.user = data.user || '';
  }

  get formResponseDto(): FormResponseDto {
    return {
      formId: this.formId,
      user: this.user,
      answers: this.userFormResponse,
      submittedAt: new Date(),
    };
  }
}
