import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-liste-centres',
  templateUrl: './liste-centres.component.html',
  styleUrls: ['./liste-centres.component.css']
})
export class ListeCentresComponent implements OnInit {
  titre:string="CovidApi";
  nomSearchBar = "Trouver un centre de vaccination";
  constructor() { }

  ngOnInit(): void {
  }

}
