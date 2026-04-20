import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BookingService } from '../booking.service';

@Component({
  selector: 'app-mybookings',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './mybookings.component.html',
  styleUrl: './mybookings.component.css'
})
export class MybookingsComponent implements OnInit {

  bookings: any[] = [];

  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {
    const custId = this.bookingService.custId 
                  || Number(localStorage.getItem('custId'));

    console.log("CustId:", custId);

    if (!custId) {
      console.error("Customer ID not found. Please log in.");
      return;
    }

    this.bookingService.getBookingsByCustomerId(custId)
      .subscribe({
        next: (data) => {
          console.log("Bookings:", data);
          this.bookings = data;
        },
        error: (err) => {
          console.error("Error fetching bookings:", err);
        }
      });
  }
}