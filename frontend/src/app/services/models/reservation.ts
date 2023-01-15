import {Centre} from "./centre";
import {Patient} from "./patient";

export interface Reservation {
	id: number;
	centre: Centre;
	patient: Patient;
	faite: boolean;
}
