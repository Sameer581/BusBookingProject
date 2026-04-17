import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { BookingService } from '../booking.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginData = {
    username: '',
    password: '',
  };

  errorMessage: string = '';
  res: boolean = false;

  constructor(
    private auth: BookingService,
    private router: Router,
  ) {}

  doLogin() {
    console.log(this.loginData);

    this.auth.doLogin(this.loginData).subscribe({
      next: (response: any) => {
        this.res = true;

        let token = response.token;
        console.log('Login successful:', response);

        this.auth.token = token;
        this.auth.username = response.username;

        this.router.navigate(['/schedule']);
      },
      error: (error) => {
        console.error('Login failed:', error);
        this.errorMessage = 'Invalid username or password';
        this.res = false;
      },
    });
  }
}
