import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../service/register/register.service';
import {
  FormGroup,
  Validators,
  FormBuilder
} from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  validateForm: FormGroup;
  failedLogIn = false;

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
          if (response.status === 200) {
            console.log('User: ' + response.user.name + ' Registered Successfully!');
            this.router.navigate(['/keystrokes']);
          } else if (response.status === 409) {
            this.failedLogIn = true;
            console.log('Given Email Id is Already Registered!');
          } else {
            console.log('User Registered Successfully: ' + JSON.stringify(response));
          }
        },
        err => {
          console.log('Some Error Occurred whil Processing Request: ' + JSON.stringify(err));
        }
      );
    }
  }

  constructor(private router: Router,
    private fb: FormBuilder,
    private registerService: RegisterService) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]]
    });
  }
}