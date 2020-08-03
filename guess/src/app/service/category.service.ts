import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Category } from '../model/category';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  apiUrl:string = 'http://localhost:8080/v1/api';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  getAllCategory(){
    const url = this.apiUrl + '/category';
    return this.http.get(url).pipe(map((res: any) => {
      return jQuery.map(res._embedded.categoryDTOList, (category) => {
        return new Category(category);
      });
    }));
  }
}
