import {Injectable} from '@angular/core';
import {CanLoad, Route, Router, UrlSegment, UrlTree} from '@angular/router';
import {map, Observable} from 'rxjs';
import {AuthService} from "../../services/private/auth.service";

@Injectable({
	providedIn: 'root'
})
export class PrivateGuard implements CanLoad {
	constructor(private auth: AuthService, private router: Router) {
	}

	canLoad(_route: Route, _segments: UrlSegment[]) {
		return this.auth.isAuthenticated()
			.pipe(map((b, _) => b ? true : this.router.parseUrl('/login')));
	}
}
