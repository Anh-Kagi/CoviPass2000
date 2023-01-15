import {Injectable} from '@angular/core';
import {CanLoad, Route, UrlSegment} from '@angular/router';
import {map} from 'rxjs';
import {AuthService} from "../../services/private/auth.service";
import {AdminGuard} from "../admin/admin.guard";
import {Account} from "../../services/models/account";
import {Role} from "../../services/models/role";

@Injectable({
	providedIn: 'root'
})
export class MedecinGuard implements CanLoad {
	constructor(private auth: AuthService, private aguard: AdminGuard) {
	}

	canLoad(_route: Route, _segments: UrlSegment[]) {
		return this.auth.getAccount()
			.pipe(map((a, _) => this.isAllowed(a)));
	}

	isAllowed(acc: Account | null) {
		return acc !== null && (acc.role === Role.MEDECIN || this.aguard.isAllowed(acc));
	}
}
