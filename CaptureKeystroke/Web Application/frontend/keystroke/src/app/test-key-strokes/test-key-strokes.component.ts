import { Component, OnInit } from '@angular/core';
import { Keystroke } from '../domain/keystroke.model';
import { UserKeystrokesService } from '../service/keystroke/user-keystrokes.service';
import { LocalStorageService } from '../service/storage/local-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-test-key-strokes',
  templateUrl: './test-key-strokes.component.html',
  styleUrls: ['./test-key-strokes.component.scss']
})
export class TestKeyStrokesComponent implements OnInit {

  interval;
  index = 0;
  type: string;
  timeLeft: number;
  showRadioButton = true;
  disableTextArea = true;
  timeAllowed: number = 60;
  userTypedKeystrokes: string;
  disableButtonControl = false;
  private keystrokes: Keystroke[] = [];
  freeTextTopics = ['Sports', 'Politics', 'Machine Learning', 'Data Science', 'Big Data'];
  fixedTextStatement = "A quick brown fox jumps over the lazy dog.";

  constructor(private router: Router,
    private keystrokeService: UserKeystrokesService,
    private localStorage: LocalStorageService) {
  }

  start() {

    this.index++;
    this.disableTextArea = false;
    this.timeLeft = this.timeAllowed;
    this.disableButtonControl = true;

    this.interval = setInterval(() => {
      if (this.timeLeft > 0) {
        this.timeLeft--;
      } else {
        clearInterval(this.interval);
        this.disableTextArea = true;
        this.disableButtonControl = false;
      }
    }, 1000);
  }

  next() {

    if (this.index > 5) {

      //show pop-up thank you window
      //logout option
      //file storage while creating zip

    } else {

      // Saving previous response before moving ahead

      const formData = new FormData();

      formData.append('keystrokeType', this.type);
      formData.append('enrollmentNumber', this.index.toString());
      formData.append('email', this.localStorage.getEmail());
      formData.append('keystrokes', JSON.stringify(this.keystrokes));

      this.keystrokeService.save(formData).subscribe(
        response => {
          if (response.status === 200) {

            console.log('Keystrokes Saved Successfully!');

            this.index++;
            this.keystrokes = [];
            this.disableTextArea = false;
            this.userTypedKeystrokes = "";
            this.timeLeft = this.timeAllowed;
            this.disableButtonControl = true;

            this.interval = setInterval(() => {
              if (this.timeLeft > 0) {
                this.timeLeft--;
              } else {
                clearInterval(this.interval);
                this.disableTextArea = true;
                this.disableButtonControl = false;
              }
            }, 1000);

          } else {
            console.log('Keystrokes Save Response: ' + JSON.stringify(response));
          }
        },
        err => {
          console.log('Some Error Occurred while Saving Keystrokes: ' + JSON.stringify(err.error.message));
        }
      );
    }
  }

  typeSelected() {
    this.showRadioButton = false;
  }

  keystroked(keyPressed) {

    var keystroke = new Keystroke();
    keystroke.keyTyped = keyPressed.key
    keystroke.keystrokeEvent = 'pressed'
    keystroke.timeStamp = Date.now();

    this.keystrokes.push(keystroke);
  }

  ngOnInit() {

    const authEmail = this.localStorage.getEmail();

    if (!authEmail) {
      this.router.navigate(['/']);
    }
  }
}