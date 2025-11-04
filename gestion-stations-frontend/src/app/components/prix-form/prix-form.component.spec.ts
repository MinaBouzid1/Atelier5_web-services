import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrixFormComponent } from './prix-form.component';

describe('PrixFormComponent', () => {
  let component: PrixFormComponent;
  let fixture: ComponentFixture<PrixFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrixFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrixFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
