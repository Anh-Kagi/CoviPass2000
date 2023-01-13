import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MedecinService} from "./medecin/medecin.service";

@Injectable({
	providedIn: 'root'
})
export class PrivateService {
	constructor(private http: HttpClient, public medecin: MedecinService) {
	}
}
