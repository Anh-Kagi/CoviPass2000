import {Component, OnInit} from '@angular/core';
import {AuthService, Role} from "./services/private/auth.service";
import {Router} from "@angular/router";

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
	title = 'covid-front';
	titre: string = "CovidApi";

	protected role!: Role;

	constructor(private auth: AuthService, private router: Router) {
	}

	ngOnInit() {
		this.auth.getRole().subscribe(role => this.role = role);
		this.auth.updatedRole.subscribe(r => this.role = r);
	}

	protected authenticated() {
		return this.role !== Role.GUEST;
	}

	protected logout() {
		this.auth.logout().subscribe();
		this.router.navigate(['']).then(() => {
		});
	}
}
