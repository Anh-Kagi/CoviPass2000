import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {map, Observable} from 'rxjs';
import {AuthService} from "../../services/private/auth.service";

@Injectable({
	providedIn: 'root'
})
export class LoginGuard implements CanActivate {
	constructor(private auth: AuthService, private router: Router) {
	}

	canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
		return this.auth.isAuthenticated()
			.pipe(map((r, _) => r ? this.router.parseUrl('/admin') : true));
	}
}
