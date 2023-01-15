import {NgModule} from "@angular/core";
import {RouterModule, Routes} from '@angular/router'
import {NotFoundComponent} from "./not-found/not-found.component";
import {PrivateGuard} from "./guards/private/private.guard";

const routes: Routes = [
	{path: '', loadChildren: () => import('./public/public.module').then(m => m.PublicModule)},
	{
		path: 'private',
		loadChildren: () => import('./private/private.module').then(m => m.PrivateModule),
		canLoad: [PrivateGuard],
		canActivate: [PrivateGuard]
	},
	{path: '**', component: NotFoundComponent}
];

@NgModule({
	imports: [RouterModule.forRoot(routes, {useHash: true})],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
