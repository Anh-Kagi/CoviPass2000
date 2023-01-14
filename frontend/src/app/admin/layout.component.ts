import {Component, OnInit} from '@angular/core';

@Component({
	selector: 'app-layout',
	templateUrl: './layout.component.html',
	styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
	protected links = [
		{path: 'patients', label: 'Patients', disabled: false},
		{path: 'unauthorized', label: 'Non autorisé', disabled: true},
	];

	protected active = '';

	constructor() {
	}

	ngOnInit(): void {
	}

}
