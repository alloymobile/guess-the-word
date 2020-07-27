import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  apiUrl: string = 'http://localhost:8080/v1/api/game';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  getScore(){
    return this.http.get(`${this.apiUrl}`);
  }
}
