import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  token?: string;
  username?: string;
  constructor(public http: HttpClient) {}

  doLogin(credentials: any): Observable<any> {
    return this.http.post('http://localhost:8080/generateToken', credentials);
  }

  getSchedules(): Observable<any> {
    return this.http.get('http://localhost:8080/schedule/viewall');
  }
}
