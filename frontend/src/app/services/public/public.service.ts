import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Centre} from "../models/centre";

@Injectable({
	providedIn: 'root'
})
export class PublicService {
	constructor(private http: HttpClient) {
	}

	public listCentre(ville: string) {
		let params = new HttpParams();
		params = params.append("ville", ville.trim().toLowerCase());

		return this.http.get<Centre[]>("/public/centre/",
			{
				params,
				responseType: "json",
			});
	}

	public inscrire(centre: number, nom: string, prenom: string, mail: string, telephone: string) {
		let data = {
			centre: centre,
			nom: nom.trim().toLowerCase(),
			prenom: prenom.trim().toLowerCase(),
			mail: mail.trim().toLowerCase(),
			telephone: telephone.trim().toLowerCase(),
		};
		return this.http.post("/public/inscrire/", data);
	}
}
