import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveVentaComponent } from './save-venta.component';

describe('SaveVentaComponent', () => {
  let component: SaveVentaComponent;
  let fixture: ComponentFixture<SaveVentaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveVentaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveVentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
