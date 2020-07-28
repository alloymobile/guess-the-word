import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Game } from '../model/game';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class GameService {
  apiUrl  = 'http://localhost:8080/v1/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  getAllGame(){
    const url = this.apiUrl + '/game';
    return this.http.get(url).pipe(map((res: any) => {
      return jQuery.map(res._embedded.gameDTOList, (game) => {
        return new Game(game);
      });
    }));
  }
}
