import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddPantryComponent } from './dialog-add-pantry.component';

describe('DialogAddPantryComponent', () => {
  let component: DialogAddPantryComponent;
  let fixture: ComponentFixture<DialogAddPantryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogAddPantryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddPantryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
