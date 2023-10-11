import { Ingredient } from "./Ingredient";
import { Meal } from "./Meal";

export class Pot {
    
    private readonly ingredients: Ingredient[] = [];

    public add(e: Ingredient): void {
        this.ingredients.push(e);
    }

    public addAll(c: Ingredient[]): void {
        this.ingredients.push(...c);
    }

    public cook(): Meal {
        console.log(`cooking ${this.ingredients} - stir stir stir`);
        return new Meal();
    }
}
