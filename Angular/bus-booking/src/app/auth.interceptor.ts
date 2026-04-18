import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { BookingService } from './booking.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  if (req.url.includes('/generateToken')) {
    return next(req);
  }
  const bookingService = inject(BookingService);

  const modifiedReq = req.clone({
    headers: req.headers.set('Authorization', `Bearer ${bookingService.token}`),
  });

  return next(modifiedReq);
};
