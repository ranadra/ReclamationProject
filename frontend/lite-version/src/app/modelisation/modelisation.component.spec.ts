import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelisationComponent } from './modelisation.component';

describe('ModelisationComponent', () => {
  let component: ModelisationComponent;
  let fixture: ComponentFixture<ModelisationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModelisationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModelisationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
