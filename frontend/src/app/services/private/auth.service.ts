import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, map, Observable} from "rxjs";

export enum Role {
	GUEST = 'GUEST',
	MEDECIN = 'MEDECIN',
	ADMIN = 'ADMIN',
	SADMIN = 'SUPER_ADMIN'
}

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	private role: Role | null = null;

	@Output() public updatedRole = new EventEmitter();

	constructor(private http: HttpClient) {
	}

	public logout() {
		this.role = null;
		let params = new HttpParams();
		params = params.set('logout', true);
		return this.http.get<string>("/public/arthur/", {params})
			.pipe(map((r, _) => this.parseRole(r)),
				map((r, _) => this.setRole(r))); // invalidate session
	}

	public login(username: string, password: string) {
		let headers = new HttpHeaders();
		headers = headers.set('Authorization', "Basic " + btoa(username + ":" + password));

		return this.http.get<string>("/public/arthur/", {headers}) // check creds
			.pipe(map((r, _) => this.parseRole(r)), // parse response
				map((r, _) => { // store role
					if (r !== Role.GUEST) {
						this.setRole(r);
						return true;
					}
					return false;
				}),
				catchError((err, _) => new Observable<boolean>(subscriber => {  // TODO check errors ?
					subscriber.next(false);
					subscriber.complete();
				})));
	}

	public getRole() {
		if (this.role === null) {
			return this.http.get<string>("/public/arthur/")
				.pipe(
					map((r, _) => this.parseRole(r)),
					map((r, _) => {
						this.setRole(r);
						return this.role!;
					})
				)
		}
		return new Observable<Role>(subscriber => {
			subscriber.next(this.role!);
			subscriber.complete();
		});
	}

	protected setRole(r: Role) {
		this.role = r;
		this.updatedRole.emit(this.role);
	}

	public isAuthenticated() {
		return this.getRole()
			.pipe(map((r, _) => r !== Role.GUEST));
	}

	protected parseRole(role: string) {
		switch (role) {
			case "MEDECIN":
				return Role.MEDECIN;
			case "ADMIN":
				return Role.ADMIN;
			case "SUPER_ADMIN":
				return Role.SADMIN;
			default:
				return Role.GUEST;
		}
	}
}
