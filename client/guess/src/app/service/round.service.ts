import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Round } from '../model/round';
@Injectable({
  providedIn: 'root'
})
export class RoundService {
  apiUrl = 'http://localhost:8080/v1/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  getAllRound(){
    const url = this.apiUrl + '/round';
    return this.http.get(url).pipe(map((res: any) => {
      return jQuery.map(res._embedded.roundDTOList, (round) => {
        return new Round(round);
      });
    }));
  }
}
