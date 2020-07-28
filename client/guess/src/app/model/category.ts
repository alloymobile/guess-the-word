export class Category {
    id: number;
    name: string;
    constructor(category?: any) {
        if (category) {
            this.id = Number(category._embedded.metadata.id);
            this.name = category.name;
        } else{
            this.id = 0;
            this.name = '';
        }
    }
}
