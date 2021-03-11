import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PantryDetailsComponent} from './pantry-details.component';

describe('PantryDetailsComponent', () => {
  let component: PantryDetailsComponent;
  let fixture: ComponentFixture<PantryDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PantryDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PantryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
