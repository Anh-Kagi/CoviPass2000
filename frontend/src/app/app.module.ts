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
import {ModalInscriptionComponent} from './liste-centres/modal-inscription/modal-inscription.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {ModalSuccessComponent} from './liste-centres/modal-success/modal-success.component';
import {ModalFailureComponent} from './liste-centres/modal-failure/modal-failure.component';


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
		ModalInscriptionComponent,
		ModalSuccessComponent,
		ModalFailureComponent,
	],
	imports: [
		BrowserModule,
		NgbModule,
		AppRoutingModule,
		HttpClientModule,
		ReactiveFormsModule,
		BrowserAnimationsModule,
		MatDialogModule,
		MatInputModule
	],
	providers: [
		{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
	],
	bootstrap: [AppComponent]
})
export class AppModule {
}





