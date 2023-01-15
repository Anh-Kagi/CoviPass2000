import {Injectable} from '@angular/core';
import {
	ActivatedRouteSnapshot,
	CanActivate,
	CanLoad,
	Route,
	Router,
	RouterStateSnapshot,
	UrlSegment
} from '@angular/router';
import {map} from 'rxjs';
import {AuthService} from "../../services/private/auth.service";

@Injectable({
	providedIn: 'root'
})
export class PrivateGuard implements CanLoad, CanActivate {
	constructor(private auth: AuthService, private router: Router) {
	}

	canLoad(_route: Route, _segments: UrlSegment[]) {
		return this.can();
	}

	canActivate(_route: ActivatedRouteSnapshot, _state: RouterStateSnapshot) {
		return this.can();
	}

	private can() {
		return this.auth.isAuthenticated()
			.pipe(map((b, _) => b ? true : this.router.parseUrl('/login')));
	}
}
