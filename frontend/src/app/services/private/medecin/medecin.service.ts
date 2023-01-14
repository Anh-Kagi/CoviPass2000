import {Injectable} from '@angular/core';
import {AuthService} from "../auth.service";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Centre} from "../../public/public.service";

export interface Patient {
	id: number;
	prenom: string;
	nom: string;
	mail: string;
	telephone: string;
}

export interface Reservation {
	id: number;
	centre: Centre;
	patient: Patient;
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
		prenom = prenom.trim().toLowerCase();
		nom = nom.trim().toLowerCase();

		let params = new HttpParams();
		if (prenom.length > 0) {
			params = params.append("prenom", prenom);
		}
		if (nom.length > 0) {
			params = params.append("nom", nom);
		}

		let url = this.buildUrl("patient");
		return this.http.get<Patient[]>(url, {params});
	}

	public getReservations(patient: number) {
		let url = this.buildUrl("patient", String(patient), "reservations");
		return this.http.get<Reservation[]>(url);
	}

	public validateReservation(reservation: number) {
		let url = this.buildUrl("reservation", String(reservation));
		return this.http.put<Reservation>(url, undefined);
	}

	protected buildUrl(...url: string[]) {
		return "/" + this.url_base.concat(url).join("/") + "/";
	}
}
