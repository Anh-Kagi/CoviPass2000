import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PrivateRoutingModule} from "./private-routing.module";
import {PatientsComponent} from './patients/patients.component';
import {LayoutComponent} from './layout.component';

@NgModule({
	declarations: [
		PatientsComponent,
		LayoutComponent
	],
	imports: [
		CommonModule,
		PrivateRoutingModule,
	]
})
export class PrivateModule {
}
