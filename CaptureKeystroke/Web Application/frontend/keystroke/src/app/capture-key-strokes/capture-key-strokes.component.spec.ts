import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaptureKeyStrokesComponent } from './capture-key-strokes.component';

describe('CaptureKeyStrokesComponent', () => {
  let component: CaptureKeyStrokesComponent;
  let fixture: ComponentFixture<CaptureKeyStrokesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaptureKeyStrokesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaptureKeyStrokesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
