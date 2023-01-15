import {Component, OnInit} from '@angular/core';
import {Reservation} from "../../../services/models/reservation";
import {AdminService} from "../../../services/private/admin/admin.service";
import {MatSnackBar} from "@angular/material/snack-bar";

interface ReservationWithState {
	reservation: Reservation;
	todelete: boolean;
}

@Component({
	selector: 'app-reservations',
	templateUrl: './reservations.component.html',
	styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {
	protected reservations: ReservationWithState[] = [];
	protected columns = ['id', 'patient', 'centre', 'faite', 'action']

	constructor(private service: AdminService, private snackBar: MatSnackBar) {
	}

	ngOnInit(): void {
		this.syncReservations();
	}

	delete(reservation: ReservationWithState) {
		reservation.todelete = true;
		this.snackBar.open('Suppression de la réservation en cours...', 'Annuler', {duration: 5000})
			.afterDismissed()
			.subscribe(v => {
				if (!v.dismissedByAction) {
					this.service.deleteReservation(reservation.reservation.id)
						.subscribe({
							next: () => this.syncReservations(),
							error: () => {
								reservation.todelete = false;
								this.snackBar.open('Erreur lors de la suppression de la réservation', undefined, {duration: 5000});
							}
						});
				}
				reservation.todelete = false;
			});
	}

	private syncReservations() {
		this.service.listReservation()
			.subscribe(reservations => {
				this.reservations = [];
				reservations.forEach(r => {
					this.reservations.push({
						reservation: r,
						todelete: false,
					});
				});
			});
	}
}
