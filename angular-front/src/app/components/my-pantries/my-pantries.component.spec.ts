import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MyPantriesComponent} from './my-pantries.component';

describe('MyPantriesComponent', () => {
  let component: MyPantriesComponent;
  let fixture: ComponentFixture<MyPantriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyPantriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyPantriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
