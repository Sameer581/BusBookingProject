import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet, RouterLinkActive } from '@angular/router';
import { BookingService } from './booking.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, CommonModule, RouterLinkActive],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  constructor(
    public bookingService: BookingService,
    public router: Router,
  ) {}

  title = 'WorldBus';

  logout() {
    if (confirm('Are You Sure You Want To Log Out?')) {
      this.bookingService.token = undefined;
      this.bookingService.username = undefined;
      this.router.navigate(['/login']);
    }
  }


}
