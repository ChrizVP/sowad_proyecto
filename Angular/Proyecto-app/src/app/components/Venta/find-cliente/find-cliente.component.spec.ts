import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindClienteComponent } from './find-cliente.component';

describe('FindClienteComponent', () => {
  let component: FindClienteComponent;
  let fixture: ComponentFixture<FindClienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindClienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
