import {Injectable} from '@angular/core';
import {CanLoad, Route, UrlSegment} from '@angular/router';
import {map} from 'rxjs';
import {Account, AuthService, Role} from "../../services/private/auth.service";
import {SuperAdminGuard} from "../super-admin/super-admin.guard";

@Injectable({
	providedIn: 'root'
})
export class AdminGuard implements CanLoad {
	constructor(private auth: AuthService, private saguard: SuperAdminGuard) {
	}

	canLoad(_route: Route, _segments: UrlSegment[]) {
		return this.auth.getAccount()
			.pipe(map((a, _) => this.isAllowed(a)));
	}

	isAllowed(acc: Account | null) {
		return acc !== null && (acc.role === Role.ADMIN || this.saguard.isAllowed(acc));
	}
}
