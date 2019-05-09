import { Component, OnInit } from '@angular/core';
import { Keystroke } from '../domain/keystroke.model';
import { UserKeystrokesService } from '../service/keystroke/user-keystrokes.service';

@Component({
  selector: 'app-capture-key-strokes',
  templateUrl: './capture-key-strokes.component.html',
  styleUrls: ['./capture-key-strokes.component.scss']
})
export class CaptureKeyStrokesComponent implements OnInit {

  interval;
  index = 0;
  type: string;
  timeLeft: number;
  showRadioButton = true;
  disableTextArea = true;
  userTypedKeystrokes: string;
  disableButtonControl = false;
  private keystrokes: Keystroke[] = [];
  freeTextTopics = ['Sports', 'Politics', 'Machine Learning', 'Data Science', 'Big Data'];
  fixedTextStatement = "Hello My Name is Bilal Ahmed Yaseen. I'm the Student in ITU in MSDS (Data Science) Program.";

  constructor(private keystrokeService: UserKeystrokesService) {
  }

  next() {

    this.index++;
    this.timeLeft = 10;
    this.disableTextArea = false;
    this.userTypedKeystrokes = "";    
    this.disableButtonControl = true;

    var params = {
      keystrokeType: this.type,
      keystrokes: this.keystrokes,
      enrollmentNumber: this.index,      
      email: "bilalahmedpu@gmail.com"           
    }

    this.keystrokeService.save(params).subscribe(
      response => {
        if (response.status === 200) {

          console.log('Keystrokes Saved Successfully!');

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
        console.log('Some Error Occurred while Saving Keystrokes: ' + JSON.stringify(err));
      }
    );
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
  }
}