import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-centres',
  templateUrl: './admin-centres.component.html',
  styleUrls: ['./admin-centres.component.css']
})
export class AdminCentresComponent implements OnInit {
  titre:string="CovidApi";
  nomSearchBar:string = "Trouver un centre de vaccination";
  nomCentre:string = "Centre de Nancy CHU"; 
  adresseCentre:string = "11 Avenue de la République"; 
  constructor() { }

  ngOnInit(): void {
  }

}
