import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoisirCentreComponent } from './choisir-centre.component';

describe('ChoisirCentreComponent', () => {
  let component: ChoisirCentreComponent;
  let fixture: ComponentFixture<ChoisirCentreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoisirCentreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChoisirCentreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
