import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PrivateRoutingModule} from "./private-routing.module";
import {PatientsComponent} from './medecin/patients/patients.component';
import {LayoutComponent} from './layout.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatTabsModule} from "@angular/material/tabs";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCardModule} from "@angular/material/card";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {PatientsReservationComponent} from './medecin/patients/patients-reservation/patients-reservation.component';
import {MatTableModule} from "@angular/material/table";
import {CdkTableModule} from "@angular/cdk/table";
import {MatIconModule} from "@angular/material/icon";
import {MatCheckboxModule} from "@angular/material/checkbox";

@NgModule({
	declarations: [
		PatientsComponent,
		LayoutComponent,
		PatientsReservationComponent
	],
	imports: [
		CommonModule,
		PrivateRoutingModule,
		MatSidenavModule,
		MatListModule,
		MatTabsModule,
		MatFormFieldModule,
		MatCardModule,
		ReactiveFormsModule,
		MatInputModule,
		MatButtonModule,
		MatTableModule,
		CdkTableModule,
		MatIconModule,
		MatCheckboxModule,
		FormsModule,
	]
})
export class PrivateModule {
}
