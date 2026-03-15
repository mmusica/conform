/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2026-03-14 11:46:40.

export interface FormDto {
    id: number;
    name: string;
    user: string;
    modifiedAt: Date;
    components: FormComponent[];
}

export interface FormElementDto {
    name: string;
}

export interface FormResponseDto {
    formId: number;
    user: string;
    submittedAt: Date;
    answers: { [index: string]: string };
}

export interface FormComponent extends Serializable {
    label: string;
    element: string;
    values: string[];
}

export interface Serializable {
}
