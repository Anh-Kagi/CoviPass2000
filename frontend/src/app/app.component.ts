import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/private/auth.service";
import {Router} from "@angular/router";
import { HttpClient } from '@angular/common/http';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
	title = 'covid-front';
	titre: string = "CovidApi";

	protected logged = false;

	
	results;
	constructor(private auth: AuthService, private router: Router, http: HttpClient) {
		const path = 'https://github.com/Anh-Kagi/CoviPass2000';
		this.results = http.get(path);
	}

	ngOnInit() {
		this.auth.getAccount().subscribe(a => this.logged = a !== null);
		this.auth.updatedRole.subscribe(a => this.logged = a !== null);
	}

	protected authenticated() {
		return this.logged;
	}

	protected logout() {
		this.auth.logout().subscribe();
		this.router.navigate(['']).then(() => {
		});
	}
}
