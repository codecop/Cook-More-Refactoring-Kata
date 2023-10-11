import { Name } from "./Name";

/**
 * Recipe for a meal. A recipe contains the list of the names of the needed ingredients and a
 * description how to prepare them.
 */
export class Recipe {

    private readonly nameOfMeal: Name;
    private readonly ingredients: Name[];

    constructor(nameOfMeal: Name, ...ingredients: Name[]) {
        this.nameOfMeal = nameOfMeal;
        this.ingredients = ingredients;
    }

    public getNameOfMeal(): Name {
        return this.nameOfMeal;
    }

    public listIngredients(): Name[] {
        return [...this.ingredients];
    }

    public canBePreparedWith(availableIngredients: Name[]): boolean {
        return this.ingredients.every((ingredient) => availableIngredients.filter(i => i.equals(ingredient)).length > 0);
    }

    public toString(): string {
        return `Recipe to prepare ${this.nameOfMeal.toString()}`;
    }
}
