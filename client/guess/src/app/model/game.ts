import { Word } from './word';
import { Team } from './team';
import { Round } from './round';

export class Game {
    id: number;
    word: Word;
    team: Team;
    round: Round;
    constructor(game?: Game){
        if (game) {
            this.id = game.id;
            this.word = new Word(game.word);
            this.team = new Team(game.team);
            this.round = new Round(game.round);
        } else {
            this.id = 0;
            this.word = new Word();
            this.team = new Team();
            this.round = new Round();
        }
    }
}
