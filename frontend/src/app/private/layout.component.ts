import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/private/auth.service";
import {Role} from "../services/models/role";

@Component({
	selector: 'app-layout',
	templateUrl: './layout.component.html',
	styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
	protected links: { path: string, label: string, disabled: boolean }[] = [];

	protected active = '';

	constructor(private auth: AuthService) {
	}

	ngOnInit(): void {
		this.auth.getAccount()
			.subscribe(a => {
				if (a !== null) { // @ts-ignore
					switch (a.role) {
						case Role.SADMIN:
							this.links = this.links.concat(this.getMedecinRoutes(),
								this.getAdminRoutes(),
								this.getSuperAdminRoute());
							break;
						case Role.ADMIN:
							this.links = this.links.concat(this.getMedecinRoutes(),
								this.getAdminRoutes(),
								this.getUnauthorizedRoute());
							break;
						case Role.MEDECIN:
							this.links = this.links.concat(this.getMedecinRoutes(),
								this.getUnauthorizedRoute());
							break;
					}
				}
			});
	}

	private getMedecinRoutes() {
		return {path: 'medecin/patients', label: 'Patients', disabled: false};
	}

	private getAdminRoutes() {
		return [{path: 'admin/medecins', label: 'Médecins', disabled: false},
			{path: 'admin/reservations', label: 'Réservations', disabled: false}];
	}

	private getSuperAdminRoute() {
		return [{path: 'sadmin/centres', label: 'Centres', disabled: false},
			{path: 'sadmin/admins', label: 'Admins', disabled: false}];
	}

	private getUnauthorizedRoute() {
		return {path: '.', label: 'Non autorisé', disabled: true};
	}

}
