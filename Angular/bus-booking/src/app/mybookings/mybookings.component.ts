import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BookingService } from '../booking.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-mybookings',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './mybookings.component.html',
  styleUrl: './mybookings.component.css',
})
export class MybookingsComponent implements OnInit {
  bookings: any[] = [];
  scheduleId: number = 0;
  schedule: any = {};

  constructor(private bookingService: BookingService) {}

  selectedBookingId: number | null = null;
  passengers: any[] = [];

  togglePassengers(booking: any) {

    if (this.selectedBookingId === booking.bookingId) {
      this.selectedBookingId = null;
      this.passengers = [];
      return;
    }

    this.passengers = booking.passengers.map((p: any) => ({
      passengerName: p.passengerName,
      passengerAge: p.passengerAge,
      seatNo: p.seatNo   // 👈 ADD THIS
    }));

    this.selectedBookingId = booking.bookingId;
  }
  ngOnInit(): void {
    const custId = this.bookingService.custId;

    console.log('CustId:', custId);

    if (!custId) {
      console.error('Customer ID not found. Please log in.');
      return;
    }

    this.bookingService.getBookingsByCustomerId(custId).subscribe({
      next: (data) => {
        console.log('Bookings:', data);
        this.bookings = data;
      },
      error: (err) => {
        console.error('Error fetching bookings:', err);
      },
    });

  }
}
