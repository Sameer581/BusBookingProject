import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  frm: FormGroup;

  res: boolean = false;

  constructor(
    private fb: FormBuilder,
    private auth: BookingService,
    private router: Router,
  ) {
    this.frm = this.fb.group({
      uname: ['', Validators.required],
      pwd: ['', Validators.required],
    });
  }

  doLogin() {
    console.log(this.frm.value);
    let uname = this.frm.value.uname;
    let pwd = this.frm.value.pwd;
    console.log(uname, pwd);
    this.auth.doLogin({ username: uname, password: pwd }).subscribe({
      next: (response: any) => {
        this.res = true;
        let token = response.token;
        console.log('Login successful, token:', response, token);
        this.auth.token = token;
        this.auth.username = response.username;
        this.router.navigate(['/schedule']);
      },
      error: (error) => {
        console.error('Login failed:', error);
        this.res = false;
      },
    });
  }
}
