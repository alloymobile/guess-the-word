import { Component, OnInit } from "@angular/core";
import { WordService } from "../service/word.service";
import { Word } from "../model/word";
import { Team } from "../model/team";
import { TeamService } from "../service/team.service";

@Component({
  selector: "app-game",
  templateUrl: "./game.component.html",
  styleUrls: ["./game.component.css"]
})
export class GameComponent implements OnInit {
  wordList: Word[];
  teamList: Team[];
  word: Word;
  letterLocation: number[];
  letterList: string[];
  letterSize: number;
  turnCount: number;
  scoreCount: number;
  constructor(
    private wordService: WordService,
    private teamService: TeamService
  ) {
    this.word = new Word();
    this.letterSize = 100;
    this.letterLocation = [];
    this.wordList = [];
    this.teamList = [];
    this.letterList = [];
    this.turnCount = 1;
    this.scoreCount = 0;
  }

  ngOnInit() {
    this.teamService.getAllTeam().subscribe(res => {
      this.teamList = res;
    });
    this.wordService.getAllWord().subscribe(res => {
      this.wordList = res;
    });
  }

  chooseAWord() {
    this.word = new Word();
    this.letterLocation = [];
    this.letterList = [];
    this.turnCount = 1;
    this.scoreCount = 0;

    const wordIndex = this.rand(this.wordList.length);
    this.word = this.wordList[wordIndex];
    this.wordList.splice(wordIndex, 1);
    this.scoreCount = Math.pow(2, this.word.name.length);

    this.letterSize = 100 / this.word.name.length;
    for (let i = 0; i < this.word.name.length; i++) {
      this.letterLocation.push(i);
    }

    this.letterLocation.forEach( l => {
      this.letterList.push("*");
    });
  }

  nextHint() {
    this.turnCount += 1;
    this.scoreCount = this.scoreCount / 2 ;
    const letterIndex = this.rand(this.letterLocation.length);
    const letter = this.letterLocation[letterIndex];
    this.letterList[letter] = this.word.name[letter];
    this.letterLocation.splice(letterIndex, 1);
  }

  showWord() {

  }

  // using Math.floor
  rand(maxLimit) {
    const rand = Math.random() * maxLimit;
    return Math.floor(rand);
  }
}
