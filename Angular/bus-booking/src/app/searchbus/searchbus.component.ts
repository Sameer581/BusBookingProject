import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule} from '@angular/common';

@Component({
  selector: 'app-searchbus',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './searchbus.component.html',
  styleUrl: './searchbus.component.css'
})
export class SearchbusComponent {
  source: string = '';
  destination: string = '';
  date: string = '';

  constructor(public router: Router) {}
  searchBuses(){
    this.router.navigate(['/schedule'], { queryParams: { source: this.source, destination: this.destination, date: this.date }});
  }
}
