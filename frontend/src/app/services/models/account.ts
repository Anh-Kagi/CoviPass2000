import {Centre} from "./centre";
import {Role} from "./role";

export interface Account {
	id: number;
	username: string,
	password: string,
	centre: Centre | null
	role: Role,
}
