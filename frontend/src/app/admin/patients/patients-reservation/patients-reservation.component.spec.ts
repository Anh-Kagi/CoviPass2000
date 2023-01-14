import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PatientsReservationComponent} from './patients-reservation.component';

describe('PatientReservationComponent', () => {
	let component: PatientsReservationComponent;
	let fixture: ComponentFixture<PatientsReservationComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [PatientsReservationComponent]
		})
			.compileComponents();

		fixture = TestBed.createComponent(PatientsReservationComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
