import {Injectable} from '@angular/core';
import {CanLoad, Route, UrlSegment, UrlTree} from '@angular/router';
import {map, Observable} from 'rxjs';
import {Account, AuthService, Role} from "../../services/private/auth.service";

@Injectable({
	providedIn: 'root'
})
export class SuperAdminGuard implements CanLoad {
	constructor(private auth: AuthService) {
	}

	canLoad(route: Route, segments: UrlSegment[]): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
		return this.auth.getAccount()
			.pipe(map((a, _) => this.isAllowed(a)));
	}

	isAllowed(acc: Account | null) {
		return acc !== null && acc.role === Role.SADMIN;
	}
}
