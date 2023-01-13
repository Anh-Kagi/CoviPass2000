import {Injectable} from '@angular/core';
import {AuthService} from "../auth.service";
import {HttpClient, HttpParams} from "@angular/common/http";

export interface Patient {
	id: number;
	prenom: string;
	nom: string;
	mail: string;
	telephone: string;
}

export interface Reservation {
	id: number;
	centre: number;
	patient: number;
	faite: boolean;
}

@Injectable({
	providedIn: 'root'
})
export class MedecinService {
	private url_base = ["private", "medecin"];

	constructor(private auth: AuthService, private http: HttpClient) {
	}

	public getPatients(prenom: string, nom: string) {
		let params = new HttpParams();
		params = params.append("prenom", prenom);
		params = params.append("nom", nom);

		let url = this.buildUrl("patients");
		return this.http.get<Patient[]>(url, {params});
	}

	public getReservations(patient: number) {
		let url = this.buildUrl("patient", String(patient), "reservations");
		return this.http.get<Reservation[]>(url);
	}

	public validateReservation(reservation: number) {
		let url = this.buildUrl("reservation", String(reservation));
		return this.http.patch<Reservation>(url, undefined);
	}

	protected buildUrl(...url: string[]) {
		return "/" + this.url_base.concat(url).join("/") + "/";
	}
}
