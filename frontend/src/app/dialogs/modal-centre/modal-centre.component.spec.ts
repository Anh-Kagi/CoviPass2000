import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModalCentreComponent} from './modal-centre.component';

describe('ModalCentreComponent', () => {
	let component: ModalCentreComponent;
	let fixture: ComponentFixture<ModalCentreComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [ModalCentreComponent]
		})
			.compileComponents();

		fixture = TestBed.createComponent(ModalCentreComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
