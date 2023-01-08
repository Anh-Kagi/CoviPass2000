import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ListeCentresComponent} from './liste-centres/liste-centres.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ChoisirCentreComponent} from './choisir-centre/choisir-centre.component';
import {AdminpageComponent} from './adminpage/adminpage.component';
import {AdminLoginComponent} from './admin-login/admin-login.component';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {AdminCentresComponent} from './admin-centres/admin-centres.component';
import {AppRoutingModule} from './app-routing.module';
import {CentreCardComponent} from './liste-centres/centre-card/centre-card.component';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
	declarations: [
		AppComponent,
		ListeCentresComponent,
		ChoisirCentreComponent,
		AdminpageComponent,
		AdminLoginComponent,
		AdminHomeComponent,
		AdminCentresComponent,
		CentreCardComponent,
	],
	imports: [
		BrowserModule,
		NgbModule,
		AppRoutingModule,
		HttpClientModule,
		ReactiveFormsModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {
}





