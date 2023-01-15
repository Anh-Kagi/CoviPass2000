import {Component, OnInit} from '@angular/core';
import {Account} from "../../../services/models/account";
import {AdminService} from "../../../services/private/admin/admin.service";
import {MatDialog} from "@angular/material/dialog";
import {ModalUpdateComponent} from "./modal-update/modal-update.component";

@Component({
	selector: 'app-medecins',
	templateUrl: './medecins.component.html',
	styleUrls: ['./medecins.component.css']
})
export class MedecinsComponent implements OnInit {
	protected medecins: Account[] = [];
	protected columns = ['username', 'centre', 'actions'];

	constructor(private service: AdminService, private dialog: MatDialog) {
	}

	ngOnInit(): void {
		this.syncMedecins();
	}

	edit(medecin: Account) {
		let modal = this.dialog.open(ModalUpdateComponent, {data: medecin.id});
		modal.afterClosed().subscribe(r => {
			if (r) {
				this.syncMedecins();
			}
		})
	}

	protected syncMedecins() {
		this.service.listMedecin()
			.subscribe(m => {
				this.medecins = m;
			})
	}
}
