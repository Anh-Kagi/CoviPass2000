import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ListeCentresComponent } from './liste-centres/liste-centres.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ChoisirCentreComponent } from './choisir-centre/choisir-centre.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';


@NgModule({
  declarations: [
    AppComponent,
    ListeCentresComponent,
    ChoisirCentreComponent,
    AdminpageComponent,
    AdminLoginComponent,
   
  ],
  imports: [
    BrowserModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
