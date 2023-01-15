import {NgModule} from '@angular/core';
import {RouterModule} from "@angular/router";
import {MedecinsComponent} from "./medecins/medecins.component";
import {ReservationsComponent} from "./reservations/reservations.component";

const routes = [
	{path: 'medecins', component: MedecinsComponent},
	{path: "reservations", component: ReservationsComponent},
];

@NgModule({
	declarations: [],
	imports: [
		RouterModule.forChild(routes),
	],
	exports: [RouterModule],
})
export class AdminRoutingModule {
}
