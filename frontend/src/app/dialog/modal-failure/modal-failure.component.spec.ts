import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModalFailureComponent} from './modal-failure.component';

describe('ModelFailureComponent', () => {
	let component: ModalFailureComponent;
	let fixture: ComponentFixture<ModalFailureComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [ModalFailureComponent]
		})
			.compileComponents();

		fixture = TestBed.createComponent(ModalFailureComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
