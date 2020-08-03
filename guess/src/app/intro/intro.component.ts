import { DataService } from "./../service/data.service";
import { Team } from "./../model/team";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-intro",
  templateUrl: "./intro.component.html",
  styleUrls: ["./intro.component.css"],
})
export class IntroComponent implements OnInit {
  noOfTeam: number;
  noOfRound: number;

  teamList: Team[];
  constructor(private router: Router, private dataService: DataService) {
    this.noOfRound = 1;
    this.noOfTeam = 2;
    this.teamList = [];
    for (let i = 0; i < this.noOfTeam; i++) {
      this.teamList.push(new Team());
    }
  }

  ngOnInit() {}

  createTeamList() {
    if (this.noOfTeam >= 2) {
      this.teamList = [];
      for (let i = 0; i < this.noOfTeam; i++) {
        this.teamList.push(new Team());
      }
    } else {
      this.noOfTeam = 2;
    }
  }
  startTheGame() {
    let teamName = this.teamList.filter((t) => t.name === "");
    if (teamName.length === 0) {
      this.dataService.noOfRound = this.noOfRound;
      this.dataService.teamList = this.teamList;
      this.router.navigate(["/game"]);
    } else {
      alert("Please enter Team name");
    }
  }
}
