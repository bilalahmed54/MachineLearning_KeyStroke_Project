import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ManageTopMenuService {

  testStarted: boolean;
  userLoggedIn: boolean;

  constructor() {
    this.testStarted = false;
    this.userLoggedIn = false;
  }

  hide() {
    this.userLoggedIn = false;
  }

  show() {
    this.userLoggedIn = true;
  }

  start() {
    this.testStarted = true;
  }

  stop() {
    this.testStarted = false;
  }
}