import { Component } from '@angular/core';
import { LocalStorageService } from './service/storage/local-storage.service';
import { Router } from '@angular/router';
import { ManageTopMenuService } from './service/utils/manage-top-menu.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
    constructor(private router: Router,
              private localStorageService: LocalStorageService,
              public manageTopMenuService: ManageTopMenuService) {
  }

  test() {
    this.manageTopMenuService.start();
    this.router.navigate(['keystrokes/test']);
  }

  train() {
    this.manageTopMenuService.stop();
    this.router.navigate(['keystrokes/train']);
  }

  logout(): void {
    this.manageTopMenuService.hide();
    this.localStorageService.removeEmail();
    this.router.navigate(['/']);
  }
}