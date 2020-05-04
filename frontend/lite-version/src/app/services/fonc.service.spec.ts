import { TestBed } from '@angular/core/testing';

import { FoncService } from '../layouts/admin/test-fonc/fonc.service';

describe('FoncService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FoncService = TestBed.get(FoncService);
    expect(service).toBeTruthy();
  });
});
