import {Component, OnInit} from '@angular/core';
import {MedecinService, Patient} from "../../services/private/medecin/medecin.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
	selector: 'app-patients',
	templateUrl: './patients.component.html',
	styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
	protected patients: Patient[];

	protected form = new FormGroup({
		prenom: new FormControl('', {
			nonNullable: true
		}),
		nom: new FormControl('', {
			nonNullable: true
		}),
	})

	constructor(private medecin: MedecinService) {
		this.patients = [];
	}

	ngOnInit(): void {
	}

	onSubmit() {
		this.medecin.getPatients(this.form.value.prenom!, this.form.value.nom!)
			.subscribe(p => {
				this.patients = p;
			});
	}
}
