import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserKeystrokesService {

  constructor(private http: HttpClient) {
  }

  save(params): Observable<any> {

    return this.http.post<any>(environment.apiEndpoint + 'keystroke/save',
      params,
      {}
    );
  }
}