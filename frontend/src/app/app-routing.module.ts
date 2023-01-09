import {NgModule} from "@angular/core";
import {RouterModule, Routes} from '@angular/router'
import {AdminCentresComponent} from "./admin-centres/admin-centres.component";
import {AdminHomeComponent} from "./admin-home/admin-home.component";
import { AdminLoginComponent } from "./admin-login/admin-login.component";
import {HomeComponent} from "./home/home.component";
import {ListeCentresComponent} from "./liste-centres/liste-centres.component";
import {NotFoundComponent} from "./not-found/not-found.component";

const routes: Routes = [

	{path: 'home', component:HomeComponent},
	{path: 'listeCentres', component:ListeCentresComponent},

	{path: 'adminHome',
		component:AdminHomeComponent,
		children: [
			{
				path:'Centres', component: AdminCentresComponent
			}

		]


	},
	{path: '**', component:NotFoundComponent}
];

@NgModule({
	imports: [RouterModule.forRoot(routes, {useHash: true})],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
