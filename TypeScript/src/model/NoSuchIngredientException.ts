import { Name } from "./Name";

export class NoSuchIngredientException extends Error {

    constructor(ingredient: Name) {
        super(ingredient.toString());
    }
    
}
