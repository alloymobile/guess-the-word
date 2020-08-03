import { Category } from './category';

export class Word {
    id: number;
    name: string;
    category: Category;
    constructor(word?: any){
        if (word) {
            this.id = Number(word._embedded.metadata.id);
            this.name = word.name;
            this.category = new Category(word._embedded.category);
        } else {
            this.id = 0;
            this.name = '';
            this.category = new Category();
        }
    }
}
