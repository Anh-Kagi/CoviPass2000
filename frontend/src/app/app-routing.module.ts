import {NgModule} from "@angular/core";
import {RouterModule, Routes} from '@angular/router'
import {AdminCentresComponent} from "./admin-centres/admin-centres.component";
import {AdminHomeComponent} from "./admin-home/admin-home.component";
import {HomeComponent} from "./home/home.component";
import {ListeCentresComponent} from "./liste-centres/liste-centres.component";
import {NotFoundComponent} from "./not-found/not-found.component";

const routes: Routes = [

	{path: '', component: HomeComponent},
	{path: 'centres', component: ListeCentresComponent},

	{
		path: 'admin',
		component: AdminHomeComponent,
		children: [
			{
				path: 'centres', component: AdminCentresComponent
			}

		]


	},
	{path: '**', component: NotFoundComponent}
];

@NgModule({
	imports: [RouterModule.forRoot(routes, {useHash: true})],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
