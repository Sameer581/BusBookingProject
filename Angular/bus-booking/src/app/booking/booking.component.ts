import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-moviebooking',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './booking.component.html',
  styleUrl: './booking.component.css'
})
export class MoviebookingComponent {

  rows = 10;
  cols = 4;

  bookedSeats: string[] = [];
  selectedSeats: string[] = [];

  seats: string[][] = [];

  ngOnInit() {
    this.generateSeats();
  }

  // ✅ Generate 10x4 bus layout (A1, A2, A3, A4...)
  generateSeats() {
    this.seats = [];

    for (let row = 0; row < this.rows; row++) {
      let rowSeats: string[] = [];

      for (let col = 0; col < this.cols; col++) {
        const seatLabel = `${String.fromCharCode(65 + row)}${col + 1}`;
        rowSeats.push(seatLabel);
      }

      this.seats.push(rowSeats);
    }

    console.log('Seats:', this.seats);
  }

  // ✅ Select / Deselect seat
  selectSeat(seat: string) {
    if (this.bookedSeats.includes(seat)) return;

    if (this.selectedSeats.includes(seat)) {
      this.selectedSeats = this.selectedSeats.filter(s => s !== seat);
    } else {
      this.selectedSeats.push(seat);
    }
  }

  // ✅ Book selected seats
  bookSeats() {
    if (this.selectedSeats.length === 0) {
      alert('Please select at least one seat to book.');
      return;
    }

    this.bookedSeats.push(...this.selectedSeats);
    this.selectedSeats = [];

    console.log('Booked:', this.bookedSeats);
  }

  // ✅ Clear selection only
  clearSeats() {
    this.selectedSeats = [];
  }

  // ✅ Reset everything
  resetBooking() {
    this.selectedSeats = [];
    this.bookedSeats = [];
  }
}