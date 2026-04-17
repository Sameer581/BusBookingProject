import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ScheduleComponent } from './schedule/schedule.component';

export const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'schedule', component: ScheduleComponent}
];
