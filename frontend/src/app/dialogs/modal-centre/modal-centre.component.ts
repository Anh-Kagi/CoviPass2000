import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

export type ModalCentreData = { nom: string; adresse: string; ville: string };

@Component({
	selector: 'app-modal-centre',
	templateUrl: './modal-centre.component.html',
	styleUrls: ['./modal-centre.component.css']
})
export class ModalCentreComponent implements OnInit {
	protected form = new FormGroup({
		nom: new FormControl(''),
		adresse: new FormControl(''),
		ville: new FormControl('')
	});

	constructor(private dialog: MatDialogRef<ModalCentreComponent>,
				@Inject(MAT_DIALOG_DATA) protected data: ModalCentreData) {
	}

	ngOnInit(): void {
		this.form.setValue({
			nom: this.data.nom,
			adresse: this.data.adresse,
			ville: this.data.ville
		});
	}

	protected submit() {
		this.dialog.close({
			nom: this.form.value.nom ?? '',
			adresse: this.form.value.adresse ?? '',
			ville: this.form.value.ville ?? ''
		} as ModalCentreData);
	}
}
