import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Member } from '../model/member';

@Injectable({
  providedIn: 'root'
})
export class MemeberService {
  apiUrl = 'http://localhost:8080/v1/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  getAllMember(){
    const url = this.apiUrl + '/member';
    return this.http.get(url).pipe(map((res: any) => {
      return jQuery.map(res._embedded.memberDTOList, (member) => {
        return new Member(member);
      });
    }));
  }
}
