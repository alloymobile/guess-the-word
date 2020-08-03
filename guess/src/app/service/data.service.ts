import { Team } from "./../model/team";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class DataService {
  teamList: Team[];
  noOfRound: number;
  constructor() {
    this.teamList = [];
    this.noOfRound = 1;
  }
}
