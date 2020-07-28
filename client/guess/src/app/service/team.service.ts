import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Team } from '../model/team';
@Injectable({
  providedIn: 'root'
})
export class TeamService {
  apiUrl = 'http://localhost:8080/v1/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  getAllTeam(){
    const url = this.apiUrl + '/team';
    return this.http.get(url).pipe(map((res: any) => {
      return jQuery.map(res._embedded.teamDTOList, (team) => {
        return new Team(team);
      });
    }));
  }
}
