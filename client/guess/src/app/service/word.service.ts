import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Word } from '../model/word';
@Injectable({
  providedIn: 'root'
})
export class WordService {
  apiUrl = 'http://localhost:8080/v1/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  getAllWord(){
    const url = this.apiUrl + '/word';
    return this.http.get(url).pipe(map((res: any) => {
      return jQuery.map(res._embedded.wordDTOList, (word) => {
        return new Word(word);
      });
    }));
  }
}
