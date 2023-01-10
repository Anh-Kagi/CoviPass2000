import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
	selector: 'app-model-failure',
	templateUrl: './modal-failure.component.html',
	styleUrls: ['./modal-failure.component.css']
})
export class ModalFailureComponent implements OnInit {

	constructor(private dialog: MatDialogRef<ModalFailureComponent>) {
	}

	ngOnInit(): void {
		setTimeout(() => {
			this.dialog.close();
		}, 5 * 1000);
	}
}
