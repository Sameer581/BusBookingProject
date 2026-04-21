import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../booking.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './booking.component.html',
  styleUrl: './booking.component.css',
})
export class BookingComponent implements OnInit {
  schedule: any = {};
  rows: number = 5;
  cols: number = 4;

  seats: any[][] = [];

  passengers: any[] = [];

  constructor(
    public service: BookingService,
    public route: ActivatedRoute,
    public router: Router,
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.service.getScheduleById(id).subscribe({
      next: (res) => {
        this.schedule = res;
        this.initializeSeats();
      },
      error: (err) => console.error(err),
    });
  }

  initializeSeats() {
    this.seats = [];
    this.cols = 4;
    this.rows = Math.ceil(this.schedule.totalSeats / this.cols);

    const letters = ['A', 'B', 'C', 'D'];

    for (let i = 0; i < this.rows; i++) {
      let row = [];

      for (let j = 0; j < this.cols; j++) {
        const seatNo = letters[j] + (i + 1);

        row.push({
          seatNumber: seatNo,
          isSelected: false,
          isBooked: this.schedule.bookedSeats?.includes(seatNo),
        });
      }

      this.seats.push(row);
    }
  }

  formatTime(date: string, time: string): Date {
    return new Date(date + 'T' + time);
  }

  bookSeats() {
    if (this.passengers.length === 0) {
      alert('Select seats first');
      return;
    }

    const payload = {
      scheduleId: this.schedule.scheduleId,
      custId: this.service.custId,
      bookingDt: new Date().toISOString().split('T')[0],
      passengers: this.passengers,
    };

    console.log('Booking payload:', payload);

    this.service.bookSeats(payload).subscribe({
      next: () => {
        alert('Booking successful');
        this.router.navigate(['/']);
      },
      error: (err) => console.error(err),
    });
  }

  clearSeats() {
    this.seats.forEach(row => {
    row.forEach(seat => seat.isSelected = false);
    });
    this.passengers = [];

  }

  toggleSeat(seat: any) {
    if (seat.isBooked) return;

    seat.isSelected = !seat.isSelected;

    if (seat.isSelected) {
      this.passengers.push({
        seatNo: seat.seatNumber,
        passengerName: '',
        passengerAge: null,
      });
    } else {
      this.passengers = this.passengers.filter(
        (p) => p.seatNo !== seat.seatNumber,
      );
    }
  }

  resetBooking() {
    this.initializeSeats();
    this.passengers = [];
  }
}
