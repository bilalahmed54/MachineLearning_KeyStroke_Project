import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../service/register/register.service';
import {
  FormGroup,
  Validators,
  FormBuilder
} from '@angular/forms';
import { LocalStorageService } from '../service/storage/local-storage.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  validateForm: FormGroup;
  failedLogIn = false;

  constructor(private router: Router,
    private fb: FormBuilder,
    private registerService: RegisterService,
    private localStorage: LocalStorageService) {
  }

  submitForm(): void {

    if (!this.validateForm.valid) {

      alert('Please enter valid form value(s)');

    } else {

      var params = {
        email: this.validateForm.get('email').value,
        username: this.validateForm.get('name').value
      };

      this.registerService.register(params).subscribe(
        response => {
          if (response.status === 200 || response.status === 409) {
            console.log('User Enrolled Successfully!');
            this.localStorage.saveEmail(params.email);
            this.router.navigate(['/keystrokes/train']);
          } else {
            console.log('User Registered Successfully: ' + JSON.stringify(response));
          }
        },
        err => {
          console.log('Some Error Occurred while Processing Request: ' + JSON.stringify(err));
        }
      );
    }
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]]
    });
  }
}