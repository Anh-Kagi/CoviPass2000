import {Component, OnInit} from '@angular/core';
import {MedecinService} from "../../../../services/private/medecin/medecin.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Centre} from "../../../../services/models/centre";
import {Patient} from "../../../../services/models/patient";

enum ReservationState {
	TODO,
	WAITING,
	DONE,
}

interface ReservationWithState {
	id: number;
	patient: Patient;
	centre: Centre;
	state: ReservationState;
}

@Component({
	selector: 'app-patient-reservation',
	templateUrl: './patients-reservation.component.html',
	styleUrls: ['./patients-reservation.component.css']
})
export class PatientsReservationComponent implements OnInit {
	protected reservations: ReservationWithState[] = [];
	protected columns = ["centre-address", "faite"]

	constructor(private medecin: MedecinService, private snackBar: MatSnackBar) {
	}

	public get reservationState(): typeof ReservationState {
		return ReservationState;
	}

	ngOnInit(): void {
		let patient: Patient = history.state;
		this.medecin.getReservations(patient.id)
			.subscribe(reservations => {
				this.reservations = reservations.map((r,) => {
					return {
						id: r.id,
						patient: r.patient,
						centre: r.centre,
						state: r.faite ? ReservationState.DONE : ReservationState.TODO,
					};
				});
			});
	}

	update(reservation: ReservationWithState) {
		if (reservation.state === ReservationState.TODO) {
			reservation.state = ReservationState.WAITING;
			this.snackBar.open("Validation de la réservation", "Annuler", {duration: 5000})
				.afterDismissed().subscribe(d => {
				if (!d.dismissedByAction) {
					this.medecin.validateReservation(reservation.id)
						.subscribe({
							next: _ => reservation.state = ReservationState.DONE,
							error: _ => reservation.state = ReservationState.TODO
						});
				}
				reservation.state = ReservationState.TODO;
			});
		}
	}
}
