import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MedecinsComponent} from './medecins/medecins.component';
import {ReservationsComponent} from './reservations/reservations.component';
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {AdminRoutingModule} from "./admin-routing.module";
import {MatButtonModule} from "@angular/material/button";
import {ModalUpdateComponent} from './medecins/modal-update/modal-update.component';
import {MatCardModule} from "@angular/material/card";
import {MatDialogModule} from "@angular/material/dialog";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatAutocompleteModule} from "@angular/material/autocomplete";

@NgModule({
	declarations: [
		MedecinsComponent,
		ReservationsComponent,
		ModalUpdateComponent
	],
	imports: [
		CommonModule,
		AdminRoutingModule,
		MatTableModule,
		MatIconModule,
		MatButtonModule,
		MatCardModule,
		MatDialogModule,
		ReactiveFormsModule,
		MatInputModule,
		MatAutocompleteModule
	]
})
export class AdminModule {
}
