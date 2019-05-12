import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ManageTopMenuService {

  userLoggedIn: boolean;

  constructor() {
    this.userLoggedIn = false;
  }

  hide() {
    this.userLoggedIn = false;
  }

  show() {
    this.userLoggedIn = true;
  }
}