import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ScoreService {
  apiUrl: string = 'http://localhost:8080/v1/api/game';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  getScore(){
    return this.http.get(`${this.apiUrl}`);
  }
}
