import {Component, OnInit} from '@angular/core';
import {Account} from "../../../services/models/account";
import {AdminService} from "../../../services/private/admin/admin.service";
import {MatDialog} from "@angular/material/dialog";
import {ModalAccountComponent, ModalAccountData} from "../../../dialogs/modal-account/modal-account.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
	selector: 'app-medecins',
	templateUrl: './medecins.component.html',
	styleUrls: ['./medecins.component.css']
})
export class MedecinsComponent implements OnInit {
	protected medecins: Account[] = [];
	protected columns = ['create', 'username', 'centre', 'actions'];

	constructor(private service: AdminService, private dialog: MatDialog, private snackBar: MatSnackBar) {
	}

	ngOnInit(): void {
		this.syncMedecins();
	}

	edit(medecin: Account) {
		this.dialog.open(ModalAccountComponent, {
			data: {
				required: false, callback: (m: ModalAccountData) => {
					this.snackBar.open("Modification en cours...", "Annuler", {duration: 5000})
						.afterDismissed()
						.subscribe(v => {
							if (!v.dismissedByAction)
								this.service.updateMedecin(medecin.id, m.username, m.password, m.centre?.id ?? null)
									.subscribe(() => {
										this.syncMedecins();
									});
						});
				}
			}
		});
	}

	protected syncMedecins() {
		this.service.listMedecin()
			.subscribe(m => {
				this.medecins = m;
			})
	}

	protected create() {
		this.dialog.open(ModalAccountComponent, {
			data: {
				required: true, callback: (m: ModalAccountData) => {
					this.snackBar.open("Création en cours...", "Annuler", {duration: 5000})
						.afterDismissed()
						.subscribe(v => {
							if (!v.dismissedByAction)
								this.service.createMedecin(m.username!, m.password!, m.centre!.id)
									.subscribe(() => {
										this.syncMedecins();
									});
						});
				}
			}
		});
	}
}
