import { NgModule } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { CaptureKeyStrokesComponent } from './capture-key-strokes/capture-key-strokes.component';
import { TestKeyStrokesComponent } from './test-key-strokes/test-key-strokes.component';

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'keystrokes/test', component: TestKeyStrokesComponent },
  { path: 'keystrokes/train', component: CaptureKeyStrokesComponent },
  { path: '', redirectTo: 'register', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

  //If user types any invalid URL then it will be redirected to the default URL
  constructor(private router: Router) {
    this.router.errorHandler = (error: any) => {
      this.router.navigate(['']);
    };
  }
}