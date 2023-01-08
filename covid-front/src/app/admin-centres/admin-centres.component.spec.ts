import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCentresComponent } from './admin-centres.component';

describe('AdminCentresComponent', () => {
  let component: AdminCentresComponent;
  let fixture: ComponentFixture<AdminCentresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminCentresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCentresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
