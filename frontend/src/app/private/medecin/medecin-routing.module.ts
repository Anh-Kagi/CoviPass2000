import {NgModule} from '@angular/core';
import {RouterModule} from "@angular/router";
import {PatientsComponent} from "./patients/patients.component";
import {PatientsReservationComponent} from "./patients/patients-reservation/patients-reservation.component";

const routes = [
	{path: 'patients', component: PatientsComponent},
	{path: 'reservations', component: PatientsReservationComponent},
]

@NgModule({
	declarations: [],
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class MedecinRoutingModule {
}
