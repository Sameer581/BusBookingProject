import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  errorMessage: string = '';
  constructor(private router: Router) { }

  doSignup(){
    console.log(this.signupData),
     this.router.navigate(['/login']);
  }

  signupData = {
    username: '',
    password: '',
    custName: '',
    phoneNo: '',
  };
 



}
