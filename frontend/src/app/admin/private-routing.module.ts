import {NgModule} from "@angular/core";
import {RouterModule, Routes} from '@angular/router'
import {LayoutComponent} from "./layout.component";
import {MedecinGuard} from "../guard/medecin/medecin.guard";

const routes: Routes = [
	{
		path: '',
		component: LayoutComponent,
		children: [
			{
				path: 'patients',
				loadChildren: () => import('./patients/patients.module').then(m => m.PatientsModule),
				canLoad: [MedecinGuard]
			},
		],
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PrivateRoutingModule {
}
