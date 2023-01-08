import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-choisir-centre',
  templateUrl: './choisir-centre.component.html',
  styleUrls: ['./choisir-centre.component.css']
})
export class ChoisirCentreComponent implements OnInit {
  titre:string="CovidApi";
  nomCentre = "Centre de Nancy CHU"; 
  adresseCentre = "11 Avenue de la République"; 
  constructor() { }

  ngOnInit(): void {
  }

}
