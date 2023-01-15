import {NgModule} from '@angular/core';
import {RouterModule} from "@angular/router";
import {CentresComponent} from "./centres/centres.component";
import {AdminsComponent} from "./admins/admins.component";

const routes = [
	{path: 'centres', component: CentresComponent},
	{path: 'admins', component: AdminsComponent},
];

@NgModule({
	declarations: [],
	imports: [
		RouterModule.forChild(routes),
	],
	exports: [
		RouterModule,
	]
})
export class SuperAdminRoutingModule {
}
