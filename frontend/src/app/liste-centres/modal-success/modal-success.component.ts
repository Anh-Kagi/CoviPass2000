import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
	selector: 'app-modal-success',
	templateUrl: './modal-success.component.html',
	styleUrls: ['./modal-success.component.css']
})
export class ModalSuccessComponent implements OnInit {

	constructor(private dialog: MatDialogRef<ModalSuccessComponent>) {
	}

	ngOnInit(): void {
		setTimeout(() => {
			this.dialog.close();
		}, 3 * 1000);
	}
}
