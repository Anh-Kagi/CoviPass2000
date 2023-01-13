import {NgModule} from "@angular/core";
import {RouterModule, Routes} from '@angular/router'
import {LoginComponent} from "../public/login/login.component";
import {AdminHomeComponent} from "../admin-home/admin-home.component";
import {LayoutComponent} from "./layout.component";

const routes: Routes = [
	{
		path: '',
		component: LayoutComponent,
		children: [
			{path: '', component: AdminHomeComponent},
		],
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PrivateRoutingModule {
}
