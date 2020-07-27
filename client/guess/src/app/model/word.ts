import { Category } from './category';

export class Word {
    id: number;
    name: string;
    category: Category;
    constructor(word?: Word){
        if (word) {
            this.id = word.id;
            this.name = word.name;
            this.category = new Category(word.category);
        } else {
            this.id = 0;
            this.name = '';
            this.category = new Category();
        }
    }
}
