export class Round {
    id: number;
    name: string;
    constructor(round?: any){
        if (round) {
            this.id = round.id;
            this.name = round.name;
        } else {
            this.id = 0;
            this.name = '';
        }
    }
}
