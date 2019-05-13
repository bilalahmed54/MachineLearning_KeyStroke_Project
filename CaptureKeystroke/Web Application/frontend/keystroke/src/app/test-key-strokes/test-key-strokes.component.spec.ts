import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestKeyStrokesComponent } from './test-key-strokes.component';

describe('TestKeyStrokesComponent', () => {
  let component: TestKeyStrokesComponent;
  let fixture: ComponentFixture<TestKeyStrokesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestKeyStrokesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestKeyStrokesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
