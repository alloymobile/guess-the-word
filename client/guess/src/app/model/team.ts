export class Team {
    id: number;
    name: string;
    constructor(team?: any){
        if (team) {
            this.id = team.id;
            this.name = team.name;
        } else{
            this.id = 0;
            this.name = '';
        }
    }
}
