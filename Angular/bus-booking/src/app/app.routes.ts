import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { authGuard } from './auth.guard';
import { SearchbusComponent } from './searchbus/searchbus.component';
import { BookingComponent } from './booking/booking.component';
import { SignupComponent } from './signup/signup.component';
import { MybookingsComponent } from './mybookings/mybookings.component';

export const routes: Routes = [
  {
    path: '',
    component: SearchbusComponent,
    pathMatch: 'full',
    canActivate: [authGuard],
  },
  {path: 'signup', component: SignupComponent},
  { path: 'login', component: LoginComponent },
  { path: 'search', component: SearchbusComponent, canActivate: [authGuard] },
  { path: 'schedule', component: ScheduleComponent, canActivate: [authGuard] },
  {
    path: 'booking/:id',
    component: BookingComponent,
    canActivate: [authGuard],
  },
  {path: 'my-bookings', component: MybookingsComponent, canActivate: [authGuard]},
];
