import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() { }

  saveEmail(email: string): void {
    console.log('Email Saved on Local Storage');
    localStorage.setItem('email', email);
  }

  getEmail(): string {
    const email = localStorage.getItem('email');
    console.log('Email Retreived from Local Storage: ' + email);
    return email;
  }

  removeEmail(): void {
    console.log('Email Removed from Local Storage');
    localStorage.removeItem('email');
  }
}