import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CentresComponent} from './centres/centres.component';
import {AdminsComponent} from './admins/admins.component';
import {SuperAdminRoutingModule} from "./super-admin-routing.module";
import {MatIconModule} from "@angular/material/icon";
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";


@NgModule({
	declarations: [
		CentresComponent,
		AdminsComponent
	],
	imports: [
		CommonModule,
		SuperAdminRoutingModule,
		MatIconModule,
		MatTableModule,
		MatButtonModule,
	]
})
export class SuperAdminModule {
}
