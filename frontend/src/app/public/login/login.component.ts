import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/private/auth.service";
import {MatDialog} from "@angular/material/dialog";
import {ModalFailureComponent} from "../../dialogs/modal-failure/modal-failure.component";
import {Router} from "@angular/router";

@Component({
	selector: 'app-home',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	protected form = new FormGroup({
		username: new FormControl('', {
			nonNullable: true, validators: [
				Validators.required
			]
		}),
		password: new FormControl('', {
			nonNullable: true, validators: [
				Validators.required
			]
		})
	});

	protected visiblePassword = false;

	constructor(private auth: AuthService, private dialog: MatDialog, private router: Router) {
	}

	ngOnInit(): void {
	}

	togglePasswordVisibility() {
		this.visiblePassword = !this.visiblePassword;
	}

	login() {
		this.auth.login(this.form.value.username!, this.form.value.password!)
			.subscribe(b => {
				if (b) {
					this.router.navigate(['private'])
						.then(() => {
						});
				} else {
					this.dialog.open(ModalFailureComponent)
						.afterClosed()
						.subscribe(() => {
							this.form.reset(); // TODO: on laisse ?
						});
				}
			});
	}
}
