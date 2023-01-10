import {Component, Input, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ModalInscriptionComponent} from "../modal-inscription/modal-inscription.component";
import {ModalSuccessComponent} from "../modal-success/modal-success.component";

@Component({
	selector: 'app-centre-card',
	templateUrl: './centre-card.component.html',
	styleUrls: ['./centre-card.component.css']
})
export class CentreCardComponent implements OnInit {
	@Input() nom!: String;
	@Input() adresse!: String;
	@Input() ville!: String;
	@Input() id!: number;

	constructor(private dialog: MatDialog) {
	}

	ngOnInit(): void {
	}

	openModal() {
		let inscription = this.dialog.open(ModalInscriptionComponent, {
			data: this.id
		});
		inscription.afterClosed().subscribe(result => {
			if (result) {
				this.dialog.open(ModalSuccessComponent);
			}
		})
	}
}
