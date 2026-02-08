/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2026-01-07 16:20:55.

export interface FormDto {
    id: string;
    name: string;
    user: string;
    modifiedAt: Date;
    components: FormComponent[];
}

export interface FormResponseDto {
    formId: string;
    user: string;
    submittedAt: Date;
    answers: { [index: string]: any };
}

export interface FormComponent {
    name: string;
    type: HtmlElement;
    label: string;
    values: string[];
}

export type HtmlElement = "textarea" | "select" | "radio";
