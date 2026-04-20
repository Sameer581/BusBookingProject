import { Component } from '@angular/core';
import { BookingService } from '../booking.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-schedule',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './schedule.component.html',
  styleUrl: './schedule.component.css',
})
export class ScheduleComponent {
  schedules: any[] = [];
  source: string = '';
  dest: string = '';
  date: string = '';

  constructor(
    private service: BookingService,
    private route: ActivatedRoute,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.source = params['source'] || '';
      this.dest = params['destination'] || '';
      this.date = params['date'] || '';
    });
    this.loadSchedules();
  }

  loadSchedules() {
    this.service.getSchedules(this.source, this.dest, this.date).subscribe({
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

  goToBooking(schedule: any) {
    console.log('Selected:', schedule);

    this.router.navigate(['/booking', schedule.scheduleId]);
  }
}
