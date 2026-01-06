import { Routes } from '@angular/router';
import { FormOverview } from './components/form-overview/form-overview';
import { formResolver } from './resolvers/form-resolver';

export const routes: Routes = [
  {
    path: 'forms',
    component: FormOverview,
  },
  {
    path: 'forms/:id',
    loadComponent: () =>
      import('./components/form-renderer/form-renderer').then(
        (m) => m.FormRenderer,
      ),
    resolve: {
      form: formResolver,
    },
  },
];
