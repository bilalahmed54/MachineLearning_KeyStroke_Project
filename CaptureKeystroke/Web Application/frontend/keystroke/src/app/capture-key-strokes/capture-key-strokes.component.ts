import { Component, OnInit } from '@angular/core';
import { Keystroke } from '../domain/keystroke.model';

@Component({
  selector: 'app-capture-key-strokes',
  templateUrl: './capture-key-strokes.component.html',
  styleUrls: ['./capture-key-strokes.component.scss']
})
export class CaptureKeyStrokesComponent implements OnInit {

  interval;
  index = 0;
  timeLeft: number;
  showRadioButton = true;
  disableTextArea = true;  
  disableButtonControl = false;
  private keystrokes: Keystroke[] = [];
  freeTextTopics = ['Sports', 'Politics', 'Machine Learning', 'Data Science', 'Big Data'];
  fixedTextStatement = "Hello My Name is Bilal Ahmed Yaseen. I'm the Student in ITU in MSDS (Data Science) Program.";

  constructor() {
  }

  next() {

    this.index++;
    this.timeLeft = 10;
    this.disableTextArea = false;
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

  typeSelected() {
    this.showRadioButton = false;
  }

  keystroked(keyPressed) {

    var keystroke = new Keystroke();
    keystroke.keyTyped = keyPressed.key
    keystroke.keystrokeEvent = 'pressed'
    keystroke.timeStamp = Date.now();

    this.keystrokes.push();
  }

  ngOnInit() {
  }
}