import { Word } from './word';
import { Team } from './team';
import { Round } from './round';

export class Game {
    id: number;
    word: Word;
    team: Team;
    round: Round;
    constructor(game?: any){
        if (game) {
            this.id = game.id;
            this.word = new Word(game._embedded.word);
            this.team = new Team(game._embedded.team);
            this.round = new Round(game._embedded.round);
        } else {
            this.id = 0;
            this.word = new Word();
            this.team = new Team();
            this.round = new Round();
        }
    }
}
