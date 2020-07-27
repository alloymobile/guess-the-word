import { Team } from './team';

export class Member {
    id: number;
    name: string;
    team: Team;
    constructor(member?: any){
        if (member) {
            this.id = member.id;
            this.name = member.name;
            this.team = new Team(member._embedded.team);
        } else {
            this.id = 0;
            this.name = '';
            this.team = new Team();
        }
    }
}
