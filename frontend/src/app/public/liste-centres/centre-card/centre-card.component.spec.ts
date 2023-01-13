import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CentreCardComponent} from './centre-card.component';

describe('CentreCardComponent', () => {
	let component: CentreCardComponent;
	let fixture: ComponentFixture<CentreCardComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [CentreCardComponent]
		})
			.compileComponents();

		fixture = TestBed.createComponent(CentreCardComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
