import {NgModule} from "@angular/core";
import {RouterModule, Routes} from '@angular/router'
import {ListeCentresComponent} from "./liste-centres/liste-centres.component";
import {LoginComponent} from "./login/login.component";
import {LoginGuard} from "../guards/login/login.guard";

const routes: Routes = [
	{path: '', component: ListeCentresComponent},
	{path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
	//{path: 'logout', component: }
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PublicRoutingModule {
}
