import { Component, OnInit } from '@angular/core';
import { Keystroke } from '../domain/keystroke.model';
import { UserKeystrokesService } from '../service/keystroke/user-keystrokes.service';
import { LocalStorageService } from '../service/storage/local-storage.service';
import { Router } from '@angular/router';
import { ManageTopMenuService } from '../service/utils/manage-top-menu.service';

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
  disableButtonControl = true;
  private keystrokes: Keystroke[] = [];
  freeTextTopics = ['Sports', 'Politics', 'Machine Learning', 'Data Science', 'Big Data'];
  fixedTextStatement = "A quick brown fox jumps over the lazy dog.";

  constructor(private router: Router,
    private localStorage: LocalStorageService,
    private keystrokeService: UserKeystrokesService,
    private manageTopMenuService: ManageTopMenuService) {
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

  testKeyStrokes() {

    // Saving previous response before moving ahead

    const formData = new FormData();

    formData.append('keystrokeType', this.type);
    formData.append('enrollmentNumber', this.index.toString());
    formData.append('email', this.localStorage.getEmail());
    formData.append('keystrokes', JSON.stringify(this.keystrokes));

    console.log('Keystrokes Saved Successfully!');

    this.index = 0;
    this.keystrokes = [];
    this.showRadioButton = true;
    this.disableTextArea = true;
    this.userTypedKeystrokes = "";
    this.disableButtonControl = true;
  }

  typeSelected() {
    this.showRadioButton = false;
    this.disableButtonControl = false;
  }

  keystroked(keyPressed) {

    var keystroke = new Keystroke();
    keystroke.keyTyped = keyPressed.key
    keystroke.keystrokeEvent = 'pressed'
    keystroke.timeStamp = Date.now();

    this.keystrokes.push(keystroke);
  }

  ngOnInit() {
    this.manageTopMenuService.start();
  }
}