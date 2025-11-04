import { TestBed } from '@angular/core/testing';

import { PrixJournalierService } from './prix-journalier.service';

describe('PrixJournalierService', () => {
  let service: PrixJournalierService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrixJournalierService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
