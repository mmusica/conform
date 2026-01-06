import {
  ActivatedRouteSnapshot,
  ResolveFn,
  RouterStateSnapshot,
} from '@angular/router';

import { FormDataService } from '../services/form-data-service';
import { inject } from '@angular/core';
import { FormDto } from '../models/model';

export const formResolver: ResolveFn<FormDto> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
) => {
  return inject(FormDataService).getFormById(route.params['id']);
};
