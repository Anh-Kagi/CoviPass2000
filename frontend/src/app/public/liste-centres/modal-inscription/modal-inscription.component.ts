import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {PublicService} from "../../../services/public/public.service";
import {catchError, throwError} from "rxjs";
import {ModalFailureComponent} from "../../../dialog/modal-failure/modal-failure.component";

@Component({
	selector: 'app-modal-inscription',
	templateUrl: './modal-inscription.component.html',
	styleUrls: ['./modal-inscription.component.css']
})
export class ModalInscriptionComponent implements OnInit {
	protected form = this.formBuilder.group({
		prenom: new FormControl('', [
			Validators.required
		]),
		nom: new FormControl('', [
			Validators.required
		]),
		mail: new FormControl('', [
			Validators.email,
			Validators.required
		]),
		tel: new FormControl('', [
			Validators.required
		])
	});

	constructor(private formBuilder: FormBuilder,
				private dialogSelf: MatDialogRef<ModalInscriptionComponent>,
				private service: PublicService,
				private dialog: MatDialog,
				@Inject(MAT_DIALOG_DATA) private centre: number) {
	}

	ngOnInit(): void {
	}

	sendReservation() {
		let data = this.form.value;
		this.service.inscrire(this.centre, data.nom!, data.prenom!, data.mail!, data.tel!)
			.pipe(
				catchError((err, o) => {
					this.dialog.open(ModalFailureComponent);
					return throwError(() => new Error('Erreur lors de l\'inscription'));
				})
			)
			.subscribe(() => {
				this.dialogSelf.close(true);
			});
	}
}
