export class Team {
  id: number;
  name: string;
  constructor(team?: any) {
    if (team) {
      this.id = Number(team._embedded.metadata.id);
      this.name = team.name;
    } else {
      this.id = 0;
      this.name = "";
    }
  }
}
