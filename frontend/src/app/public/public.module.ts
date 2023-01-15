import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PublicRoutingModule} from "./public-routing.module";
import {LogoutComponent} from './logout/logout/logout.component';

@NgModule({
	declarations: [
		LogoutComponent,
	],
	imports: [
		CommonModule,
		PublicRoutingModule,
	],
})
export class PublicModule {
}
