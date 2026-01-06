/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2026-01-06 14:23:47.

export interface FormDto {
    id: ObjectId;
    name: string;
    user: string;
    modifiedAt: Date;
    components: FormComponent[];
}

export interface FormResponseDto {
    formId: ObjectId;
    user: string;
    submittedAt: Date;
    answers: { [index: string]: any };
}

export interface ObjectId extends Comparable<ObjectId>, Serializable {
    timestamp: number;
    date: Date;
}

export interface FormComponent {
    name: string;
    type: HtmlElement;
    label: string;
    values: string[];
}

export interface Serializable {
}

export interface Comparable<T> {
}

export type HtmlElement = "textarea" | "select" | "radio";
