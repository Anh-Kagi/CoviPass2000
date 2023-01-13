import {Component, Injectable, OnInit} from '@angular/core';
import {Centre, PublicService} from "../../services/public/public.service";
import {FormBuilder} from "@angular/forms";

@Injectable()
@Component({
	selector: 'app-liste-centres',
	templateUrl: './liste-centres.component.html',
	styleUrls: ['./liste-centres.component.css'],
})
export class ListeCentresComponent implements OnInit {
	centres: Centre[] = [];

	searchForm = this.formBuilder.group({
		ville: ''
	})

	constructor(private service: PublicService, private formBuilder: FormBuilder) {
	}

	ngOnInit(): void {
	}

	onSubmit() {
		this.listCentre();
	}

	protected listCentre() {
		let ville = this.searchForm.value.ville;
		if (ville === null || ville === undefined) {
			return;
		}
		this.service.listCentre(ville.trim()).subscribe((data: Centre[]) => {
			this.centres = data;
		});
	}
}
