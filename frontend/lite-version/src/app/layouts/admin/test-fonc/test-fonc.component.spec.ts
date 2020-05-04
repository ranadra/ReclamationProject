import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestFoncComponent } from './test-fonc.component';

describe('TestFoncComponent', () => {
  let component: TestFoncComponent;
  let fixture: ComponentFixture<TestFoncComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestFoncComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestFoncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
