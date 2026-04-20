import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  token?: string;
  username?: string;
  custId?: number;
  constructor(public http: HttpClient) {}

  doLogin(credentials: any): Observable<any> {
    return this.http.post('http://localhost:8080/generateToken', credentials);
  }

  getSchedules(src: string, dest: string, date: string): Observable<any> {
    let params = new HttpParams()
      .set('src', src)
      .set('dest', dest)
      .set('scheduleDate', date);

    return this.http.get('http://localhost:8080/schedule/view', {
      params: params,
    });
  }

  getScheduleById(id: number): Observable<any> {
    return this.http.get('http://localhost:8080/schedule/view/' + id);
  }

  bookSeats(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/booking/book', payload);
  }
}
