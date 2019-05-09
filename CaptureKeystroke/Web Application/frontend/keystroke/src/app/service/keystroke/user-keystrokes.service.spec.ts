import { TestBed } from '@angular/core/testing';

import { UserKeystrokesService } from './user-keystrokes.service';

describe('UserKeystrokesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserKeystrokesService = TestBed.get(UserKeystrokesService);
    expect(service).toBeTruthy();
  });
});
