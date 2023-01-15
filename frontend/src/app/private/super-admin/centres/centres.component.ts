import {Component, OnInit} from '@angular/core';
import {Centre} from "../../../services/models/centre";
import {SuperAdminService} from "../../../services/private/super-admin/super-admin.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {ModalCentreComponent, ModalCentreData} from "../../../dialogs/modal-centre/modal-centre.component";

interface CentreWithState {
	centre: Centre,
	todelete: boolean,
}

@Component({
	selector: 'app-centres',
	templateUrl: './centres.component.html',
	styleUrls: ['./centres.component.css']
})
export class CentresComponent implements OnInit {
	protected centres: CentreWithState[] = [];
	protected columns = ['create', 'id', 'nom', 'adresse', 'ville', 'actions'];

	constructor(private service: SuperAdminService,
				private dialog: MatDialog,
				private snackBar: MatSnackBar) {
	}

	ngOnInit() {
		this.syncCentres();
	}

	protected create() {
		this.dialog.open(ModalCentreComponent, {
			data: {nom: '', adresse: '', ville: ''}
		})
			.afterClosed()
			.subscribe((d: ModalCentreData | null) => {
				if (d)
					this.snackBar.open("Création du centre " + d.nom, "Annuler", {duration: 5000})
						.afterDismissed()
						.subscribe(v => {
							if (!v.dismissedByAction)
								this.service.createCentre(d.nom, d.adresse, d.ville)
									.subscribe(() => this.syncCentres());
						});
			});
	}

	protected update(centre: CentreWithState) {
		this.dialog.open(ModalCentreComponent, {
			data: centre.centre
		})
			.afterClosed()
			.subscribe((d: ModalCentreData | null) => {
				if (d)
					this.snackBar.open("Modification du centre " + centre.centre.nom, "Annuler", {duration: 5000})
						.afterDismissed()
						.subscribe(v => {
							if (!v.dismissedByAction)
								this.service.updateCentre(centre.centre.id, d.nom, d.adresse, d.ville)
									.subscribe(() => this.syncCentres());
						});
			});
			}

	protected delete(centre: CentreWithState) {
		centre.todelete = true;
		this.snackBar.open("Suppression du centre " + centre.centre.nom, "Annuler", {duration: 5000})
			.afterDismissed()
			.subscribe({
				next: v => {
					if (!v.dismissedByAction)
						this.service.deleteCentre(centre.centre.id)
							.subscribe(() => this.syncCentres());
					centre.todelete = false;
				}, error: () => {
					centre.todelete = false;
				}
			});
	}

	private syncCentres() {
		this.service.listCentre()
			.subscribe(c => {
				this.centres = [];
				c.forEach(v => this.centres.push({
					centre: v,
					todelete: false,
				}));
			});
	}
}
