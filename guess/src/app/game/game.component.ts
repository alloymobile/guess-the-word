import { DataService } from "./../service/data.service";
import { Component, OnInit } from "@angular/core";
import { WordService } from "../service/word.service";
import { Word } from "../model/word";
import { Team } from "../model/team";
import { Round } from "../model/round";
import guessdb from "../../assets/guessdb.json";

@Component({
  selector: "app-game",
  templateUrl: "./game.component.html",
  styleUrls: ["./game.component.css"],
})
export class GameComponent implements OnInit {
  wordList: Word[];
  teamList: Team[];
  roundList: Round[];
  word: Word;
  round: Round;
  team: Team;
  letterLocation: number[];
  letterList: string[];
  letterSize: number;
  turnCount: number;
  scoreCount: number;
  answer: string;
  message: string;
  gameTurn: number;
  gamePlay: string;
  score: string[];
  scoreHead: string[];
  scoreTotal: number[];
  scoreList: string[][];

  submit: boolean;

  constructor(
    private wordService: WordService,
    private dataService: DataService
  ) {
    this.word = new Word();
    this.round = new Round();
    this.team = new Team();
    this.wordList = [];
    this.teamList = this.dataService.teamList;
    this.roundList = [];

    this.answer = "";
    this.message = "";
    this.letterSize = 100;
    this.letterLocation = [];
    this.letterList = [];
    this.turnCount = 1;
    this.scoreCount = 0;
    this.gameTurn = 0;
    this.score = [];
    this.scoreHead = [];
    this.scoreTotal = [];
    this.scoreList = [];

    this.submit = false;
    this.gamePlay = "Start";
  }

  ngOnInit() {
    this.word.category.name = "Category";
    this.message = "Press start to play";
    this.scoreHead.push("Round");
    this.letterList.push("*");
    for (let i = 0; i < this.dataService.noOfRound; i++) {
      let round: Round = new Round();
      round.name = String(i + 1);
      this.roundList.push(round);
    }

    this.wordList = jQuery.map(guessdb._embedded.wordDTOList, (w) => {
      return new Word(w);
    });

    this.wordService.getAllWord().subscribe((res) => {
      this.wordList = res;
    });
    this.teamList.forEach((t) => {
      this.scoreHead.push(t.name);
      this.scoreTotal.push(0);
    });
  }

  chooseAWord() {
    if (this.gameTurn < this.roundList.length * this.teamList.length) {
      this.round = this.roundList[
        Math.floor(this.gameTurn / this.teamList.length)
      ];
      this.team = this.teamList[
        Math.floor(this.gameTurn % this.teamList.length)
      ];
      this.word = new Word();
      this.gamePlay = "Start";
      this.letterLocation = [];
      this.letterList = [];
      this.turnCount = 1;
      this.scoreCount = 0;
      this.message = "Round " + this.round.name + " turn for " + this.team.name;
      this.answer = "";
      this.submit = true;

      if (this.gameTurn % this.teamList.length === 0) {
        this.score = [];
        this.score.push(this.round.name);
        this.scoreList.push(this.score);
      }

      const wordIndex = this.rand(this.wordList.length);
      this.word = this.wordList[wordIndex];
      this.wordList.splice(wordIndex, 1);
      this.scoreCount = Math.pow(2, this.word.name.length);

      this.letterSize = 100 / this.word.name.length;
      for (let i = 0; i < this.word.name.length; i++) {
        this.letterLocation.push(i);
      }

      this.letterLocation.forEach((l) => {
        this.letterList.push("*");
      });
      this.gameTurn++;
    }
  }

  nextHint() {
    if (this.scoreCount >= 1) {
      this.turnCount += 1;
      this.scoreCount = this.scoreCount / 2;
      const letterIndex = this.rand(this.letterLocation.length);
      const letter = this.letterLocation[letterIndex];
      this.letterList[letter] = this.word.name[letter];
      this.letterLocation.splice(letterIndex, 1);
      let astric = this.letterList.find((l) => l === "*");
      if (astric === undefined) {
        this.showWord();
      }
    }
  }

  showWord() {
    this.letterList = this.word.name.split("");
    this.scoreCount = 0;
    this.answer = "";
    this.score.push(String(this.scoreCount));
    this.submit = false;
    this.scoreTotal[(this.gameTurn - 1) % this.teamList.length] =
      this.scoreTotal[(this.gameTurn - 1) % this.teamList.length] +
      this.scoreCount;
    this.message = " Sorry " + this.scoreCount + " Points to " + this.team.name;
    this.reset();
  }

  guessNow() {
    if (this.answer.toLowerCase() === this.word.name.toLowerCase()) {
      this.letterList = this.word.name.split("");
      this.message = " Correct Answer " + this.scoreCount + " Points";
    } else {
      this.scoreCount = 0;
      this.letterList = this.word.name.split("");
      this.message = " Wrong Answer " + this.scoreCount + " Points";
    }
    this.score.push(String(this.scoreCount));
    this.submit = false;
    this.scoreTotal[(this.gameTurn - 1) % this.teamList.length] =
      this.scoreTotal[(this.gameTurn - 1) % this.teamList.length] +
      this.scoreCount;
    this.reset();
  }

  // using Math.floor
  rand(maxLimit) {
    const rand = Math.random() * maxLimit;
    return Math.floor(rand);
  }

  reset() {
    if (this.gameTurn === this.roundList.length * this.teamList.length) {
      this.gamePlay = "Replay";
      this.gameTurn = 0;
      this.scoreTotal.forEach((s) => (s = 0));
      this.scoreList = [];
      let maxIndex = this.scoreTotal.indexOf(Math.max(...this.scoreTotal));
      this.message =
        "The winner is " +
        this.teamList[maxIndex].name +
        " with a score of " +
        this.scoreTotal[maxIndex];
    }
  }
}
