import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  apiUrl:string = 'http://localhost:8080/v1/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // getAllGame(){
  //   let url = this.apiUrl + '/game';
  //   return this.http.get(url).map(res=>{
  //     return HAMMER_LOADER.create;
  //   });
  // }

  // getAllGame(){
  //   let url = this.apiUrl + '/game';
  //   return this.http.get(url).map(res=>{
  //     return jQuery.map(response._embedded.acc,(acc)=>{
  //       return HashChangeEvent.create()
  //     });
  //   });
  // }
}
