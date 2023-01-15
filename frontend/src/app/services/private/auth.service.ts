import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, map, Observable} from "rxjs";
import {Account} from "../models/account";

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	private account: Account | null = null;

	@Output() public updatedRole = new EventEmitter();

	constructor(private http: HttpClient) {
	}

	public logout() {
		this.account = null;
		let params = new HttpParams();
		params = params.set('logout', true);
		return this.http.get<Account>("/public/login/", {params})
			.pipe(map((a, _) => this.setAccount(a)), // should not happen (logout should return 401)
				catchError((_err, _granted) => new Observable<null>(subscriber => { // TODO don't show error in console
					this.setAccount(null);
					subscriber.next(null);
					subscriber.complete();
				}))); // invalidate session
	}

	public login(username: string, password: string) {
		let headers = new HttpHeaders();
		headers = headers.set('Authorization', "Basic " + btoa(username + ":" + password));

		return this.http.get<Account>("/public/login/", {headers}) // check creds
			.pipe(map((a, _) => this.setAccount(a)),// store account
				catchError((err, _) => new Observable<null>(subscriber => {
					this.setAccount(null);
					subscriber.next(null);
					subscriber.complete();
				})));
	}

	public getAccount() {
		if (this.account === null) {
			return this.http.get<Account>("/public/login/")
				.pipe(map((a, _) => {
						this.setAccount(a);
						return this.account;
					}),
					catchError((_err, _granted) => new Observable<null>(subscriber => {
						this.setAccount(null);
						subscriber.next(null);
						subscriber.complete();
					})));
		}
		return new Observable<Account | null>(subscriber => {
			subscriber.next(this.account);
			subscriber.complete();
		});
	}

	public isAuthenticated() {
		return this.getAccount()
			.pipe(map((a, _) => a !== null));
	}

	protected setAccount(a: Account | null) {
		this.account = a;
		this.updatedRole.emit(this.account);
		return this.account;
	}
}
