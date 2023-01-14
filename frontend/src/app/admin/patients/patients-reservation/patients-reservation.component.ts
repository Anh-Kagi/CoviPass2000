import {Component, OnInit} from '@angular/core';
import {MedecinService, Patient, Reservation} from "../../../services/private/medecin/medecin.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
	selector: 'app-patient-reservation',
	templateUrl: './patients-reservation.component.html',
	styleUrls: ['./patients-reservation.component.css']
})
export class PatientsReservationComponent implements OnInit {
	protected reservations: Reservation[] = [];
	protected columns = ["centre-address", "faite"]

	constructor(private medecin: MedecinService, private snackBar: MatSnackBar) {
	}

	ngOnInit(): void {
		let patient: Patient = history.state;
		this.medecin.getReservations(patient.id)
			.subscribe(reservations => {
				this.reservations = reservations;
			});
	}

	update(reservation: Reservation) {
		let snackbar = this.snackBar.open("Validation de la réservation", "Annuler", {duration: 5000});
		snackbar.afterDismissed().subscribe(d => {
			if (!d.dismissedByAction) {
				this.medecin.validateReservation(reservation.id)
					.subscribe(_ => {
						reservation.faite = true;
					});
			}
		});
	}
}
