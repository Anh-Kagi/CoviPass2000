import {Component, OnInit} from '@angular/core';
import {Account} from "../../../services/models/account";
import {MatDialog} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ModalAccountComponent, ModalAccountData} from "../../../dialogs/modal-account/modal-account.component";
import {SuperAdminService} from "../../../services/private/super-admin/super-admin.service";

@Component({
	selector: 'app-admins',
	templateUrl: './admins.component.html',
	styleUrls: ['./admins.component.css']
})
export class AdminsComponent implements OnInit {
	protected admins: Account[] = [];
	protected columns = ['create', 'username', 'centre', 'actions'];

	constructor(public service: SuperAdminService, public dialog: MatDialog, public snackBar: MatSnackBar) {
	}

	ngOnInit(): void {
		this.syncMedecins();
	}

	edit(admin: Account) {
		this.dialog.open(ModalAccountComponent, {data: admin})
			.afterClosed()
			.subscribe((m: ModalAccountData | null) => {
				if (m)
					this.snackBar.open("Modification en cours...", "Annuler", {duration: 5000})
						.afterDismissed()
						.subscribe(v => {
							if (!v.dismissedByAction)
								this.service.updateAdmin(admin.id, m.username, m.password, m.centre.id)
									.subscribe(() => {
										this.syncMedecins();
									});
						});
			});
	}

	protected syncMedecins() {
		this.service.listAdmin()
			.subscribe(m => {
				this.admins = m;
			})
	}

	protected create() {
		this.dialog.open(ModalAccountComponent, {data: {username: '', password: '', centre: ''}})
			.afterClosed()
			.subscribe((a: ModalAccountData | null) => {
				if (a)
					this.snackBar.open("Création en cours...", "Annuler", {duration: 5000})
						.afterDismissed()
						.subscribe(v => {
							if (!v.dismissedByAction)
								this.service.createAdmin(a.username, a.password, a.centre.id)
									.subscribe(() => {
										this.syncMedecins();
									});
						});
			});
	}

	protected delete(admin: Account) {
		this.snackBar.open("Suppression en cours...", "Annuler", {duration: 5000})
			.afterDismissed()
			.subscribe(v => {
				if (!v.dismissedByAction)
					this.service.deleteAdmin(admin.id)
						.subscribe(() => {
							this.syncMedecins();
						});
			});
	}
}
