import {Injectable} from '@angular/core';
import {AuthService} from "../auth.service";
import {HttpClient} from "@angular/common/http";
import {Centre} from "../../models/centre";
import {Account} from "../../models/account";

@Injectable({
	providedIn: 'root'
})
export class SuperAdminService {
	private url_base = ['private', 'sadmin'];

	constructor(private auth: AuthService, private http: HttpClient) {
	}

	public createCentre(nom: string, ville: string, adresse: string) {
		return this.http.post<Centre>(this.buildUrl('centre'), {nom, ville, adresse});
	}

	public listCentre() {
		return this.http.get<Centre[]>(this.buildUrl('centre'));
	}

	public getCentre(id: number) {
		return this.http.get<Centre>(this.buildUrl('centre', id.toString()));
	}

	public updateCentre(id: number, nom: string, ville: string, adresse: string) {
		return this.http.put<Centre>(this.buildUrl('centre', id.toString()), {nom, ville, adresse});
	}

	public deleteCentre(id: number) {
		return this.http.delete<Centre>(this.buildUrl('centre', id.toString()));
	}

	public createAdmin(username: string, password: string, centre: number) {
		return this.http.post<Account>(this.buildUrl('admin'),
			{username, password, centre});
	}

	public listAdmin() {
		return this.http.get<Account[]>(this.buildUrl('admin'));
	}

	public getAdmin(id: number) {
		return this.http.get<Account>(this.buildUrl('admin', id.toString()));
	}

	public updateAdmin(id: number, username: string, password: string, centre: number) {
		return this.http.put<Account>(this.buildUrl('admin', id.toString()),
			{username, password, centre});
	}

	public deleteAdmin(id: number) {
		return this.http.delete<Account>(this.buildUrl('admin', id.toString()));
	}

	protected buildUrl(...url: string[]) {
		return '/' + this.url_base.concat(url).join('/') + '/';
	}
}
