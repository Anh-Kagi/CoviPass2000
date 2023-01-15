import {Injectable} from '@angular/core';
import {AuthService} from "../auth.service";
import {HttpClient} from "@angular/common/http";
import {map, mergeMap, Observable} from "rxjs";
import {Reservation} from "../../models/reservation";
import {Account} from "../../models/account";
import {Role} from "../../models/role";
import {SuperAdminService} from "../super-admin/super-admin.service";
import {Centre} from "../../models/centre";

@Injectable({
	providedIn: 'root'
})
export class AdminService {
	private url_base = ['private', 'admin']

	constructor(private auth: AuthService, private http: HttpClient, private sadmin: SuperAdminService) {
	}

	public createMedecin(username: string, password: string, centre: number) {
		return this.http.post(this.buildUrl('medecin'),
			{username, password, centre});
	}

	public listMedecin() {
		return this.http.get<Account[]>(this.buildUrl('medecin'));
	}

	public getMedecin(id: number) {
		return this.http.get<Account>(this.buildUrl('medecin', String(id)));
	}

	public updateMedecin(id: number, username: string | null, password: string | null, centre: number | null) {
		return this.http.put<Account>(this.buildUrl('medecin', String(id)),
			{
				username: username === null ? undefined : username,
				password: password === null ? undefined : password,
				centre: centre === null ? undefined : centre
			});
	}

	public listReservation() {
		return this.http.get<Reservation[]>(this.buildUrl('reservation'));
	}

	public getReservation(id: number) {
		return this.http.get<Reservation>(this.buildUrl('reservation', String(id)));
	}

	public deleteReservation(id: number) {
		return this.http.delete<Reservation>(this.buildUrl('reservation', String(id)));
	}

	public getCentres() {
		return this.auth.getAccount()
			.pipe(mergeMap((a, _) => {
				if (a !== null) {
					if (a.role === Role.SADMIN) {
						return this.sadmin.listCentre(); // TODO
					} else {
						return this.auth.getAccount()
							.pipe(map((a, _) => a !== null && a.centre !== null ? [a.centre] : []));
					}
				}
				return new Observable<Centre[]>(subscriber => {
					subscriber.next([]);
					subscriber.complete();
				});
			}));
	}

	protected buildUrl(...url: string[]) {
		return '/' + this.url_base.concat(url).join('/') + '/';
	}
}
