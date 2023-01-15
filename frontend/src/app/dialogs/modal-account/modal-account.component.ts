import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Centre} from "../../services/models/centre";
import {map, Observable} from "rxjs";
import {AdminService} from "../../services/private/admin/admin.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

export type ModalAccountData = { username: string; password: string; centre: Centre };
@Component({
	selector: 'app-modal-update-account',
	templateUrl: './modal-account.component.html',
	styleUrls: ['./modal-account.component.css']
})
export class ModalAccountComponent implements OnInit {
	protected form = new FormGroup({
		username: new FormControl(''),
		password: new FormControl(''),
		centre: new FormControl<string | null | Centre>(null),
	});

	protected visiblePassword = false;

	protected centres: Centre[] = [];
	protected filteredCentres: Observable<Map<string, Centre[]>>;
	protected newValues: ModalAccountData;

	constructor(private service: AdminService,
				private dialog: MatDialogRef<ModalAccountComponent>,
				@Inject(MAT_DIALOG_DATA) protected data: ModalAccountData) {
		this.newValues = data;
		this.filteredCentres = this.form.controls.centre.valueChanges
			.pipe(map(value => this.filter(typeof value === 'string' ? value : (value ? value.nom : ''))));
		this.form.valueChanges.subscribe(() => this.newValues = this.computeNewValues());
	}

	ngOnInit(): void {
		this.form.controls.username.setValue(this.data.username);
		// TODO: select the provided centre (crashes for the moment)
		this.service.getCentres()
			.subscribe(c => {
				this.centres = c;
				this.form.controls.centre.setValue(null);
			});
	}

	public togglePasswordVisibility() {
		this.visiblePassword = !this.visiblePassword;
	}

	protected displayCentre(centre: Centre | string | null) {
		return centre ? (typeof centre === 'string' ? centre : centre.nom) : '';
	}

	protected computeNewValues() {
		let username: string = this.form.get('username')?.value ?? '';
		username = username.trim();
		username = username.length > 0 ? username : this.data.username;
		let password: string = this.form.get('password')?.value ?? '';
		password = password.trim();
		password = password.length > 0 ? password : this.data.password;
		let centre_tmp = this.form.get('centre')?.value ?? null;
		let centre: Centre = this.data.centre;
		if (centre_tmp !== null && typeof centre_tmp !== 'string')
			centre = centre_tmp;

		return {username, password, centre};
	}

	protected submit() {
		this.dialog.close(this.newValues as ModalAccountData);
	}

	private filter(value: string) {
		let centres = value ? this.centres.filter(group => group.nom.toLowerCase().includes(value.toLowerCase())) : this.centres;

		let groups = new Map<string, Centre[]>();
		centres.forEach((c,) => {
			const firstLetter = c!.nom[0].toUpperCase();
			if (!groups.has(firstLetter))
				groups.set(firstLetter, []);
			groups.get(firstLetter)!.push(c);
		});
		return groups;
	}
}
