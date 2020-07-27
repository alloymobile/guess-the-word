export class Category {
    id: number;
    name: string;
    constructor(category?: Category) {
        if (category) {
            this.id = category.id;
            this.name = category.name;
        } else{
            this.id = 0;
            this.name = '';
        }
    }
}
