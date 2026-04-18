import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { BookingService } from './booking.service';

export const authGuard: CanActivateFn = (route, state) => {
  const bookingService = inject(BookingService);
  const router = inject(Router);

  if (bookingService.token) {
    return true;
  }

  router.navigate(['/login']);
  return false;
};
