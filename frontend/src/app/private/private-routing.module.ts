import {NgModule} from "@angular/core";
import {RouterModule, Routes} from '@angular/router'
import {LayoutComponent} from "./layout.component";
import {MedecinGuard} from "../guards/medecin/medecin.guard";
import {AdminGuard} from "../guards/admin/admin.guard";
import {SuperAdminGuard} from "../guards/super-admin/super-admin.guard";

const routes: Routes = [
	{
		path: '',
		component: LayoutComponent,
		children: [{
			path: 'medecin',
			loadChildren: () => import('./medecin/medecin.module').then(m => m.MedecinModule),
			canLoad: [MedecinGuard],
		}, {
			path: 'admin',
			loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
			canLoad: [AdminGuard],
		}, {
			path: 'sadmin',
			loadChildren: () => import('./super-admin/super-admin.module').then(m => m.SuperAdminModule),
			canLoad: [SuperAdminGuard],
		}],
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PrivateRoutingModule {
}
