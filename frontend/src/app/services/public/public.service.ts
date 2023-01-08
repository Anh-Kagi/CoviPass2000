import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

export interface Centre {
	id: number;
	nom: String;
	adresse: String;
	ville: String;
}

@Injectable({
	providedIn: 'root'
})
export class PublicService {
	constructor(private http: HttpClient) {
	}

	public listCentre(ville: string) {
		let params = new HttpParams();
		params = params.append("ville", ville);

		return this.http.get<Centre[]>("/public/centre/",
			{
				params,
				responseType: "json",
			});
	}
}
