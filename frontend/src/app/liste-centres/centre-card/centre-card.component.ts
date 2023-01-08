import {Component, Input, OnInit} from '@angular/core';

@Component({
	selector: 'app-centre-card',
	templateUrl: './centre-card.component.html',
	styleUrls: ['./centre-card.component.css']
})
export class CentreCardComponent implements OnInit {
	@Input() nom!: String;
	@Input() adresse!: String;
	@Input() ville!: String;

	constructor() {
	}

	ngOnInit(): void {
	}

}
