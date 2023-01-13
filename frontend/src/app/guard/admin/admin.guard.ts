import {Injectable} from '@angular/core';
import {CanLoad, Route, UrlSegment, UrlTree} from '@angular/router';
import {map, Observable} from 'rxjs';
import {AuthService, Role} from "../../services/private/auth.service";
import {SuperAdminGuard} from "../super-admin/super-admin.guard";

@Injectable({
	providedIn: 'root'
})
export class AdminGuard implements CanLoad {
	constructor(private auth: AuthService, private saguard: SuperAdminGuard) {
	}

	canLoad(_route: Route, _segments: UrlSegment[]) {
		return this.auth.getRole()
			.pipe(map((r, _) => this.isAllowed(r)));
	}

	isAllowed(role: Role) {
		return role === Role.ADMIN || this.saguard.isAllowed(role);
	}
}
