import { TestBed } from '@angular/core/testing';

import { ManageTopMenuService } from './manage-top-menu.service';

describe('ManageTopMenuService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ManageTopMenuService = TestBed.get(ManageTopMenuService);
    expect(service).toBeTruthy();
  });
});
