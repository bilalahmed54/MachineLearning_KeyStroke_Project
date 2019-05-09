import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) {
  }

  register(params): Observable<any> {

    return this.http.post<any>(environment.apiEndpoint + 'user/save',
      {},
      {
        params: params
      });
  }
}