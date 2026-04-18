import { Component } from '@angular/core';
import { BookingService } from '../booking.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-schedule',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './schedule.component.html',
  styleUrl: './schedule.component.css',
})
export class ScheduleComponent {
  schedules: any[] = [];

  constructor(private service: BookingService) {}

  ngOnInit(): void {
    this.loadSchedules();
  }

  loadSchedules() {
    this.service.getSchedules().subscribe({
      next: (res: any) => {
        this.schedules = res;
        console.log('Schedules:', res);
      },
      error: (err) => {
        console.error('Error loading schedules', err);
      },
    });
  }

  formatTime(date: string, time: string): Date {
    return new Date(date + 'T' + time);
  }

  bookSeats(schedule: any) {
    console.log('Selected schedule:', schedule);
  
  }
}
