import { Ingredient } from "../model/Ingredient";
import { Name } from "../model/Name";
import { Recipe } from "../model/Recipe";

/**
 * Fake database code to read ingredients.
 */
export class Database {

    private readonly ingredients: Ingredient[] = [];
    private readonly recipes: Name[] = [];
    private readonly recipesByName: Map<String, Recipe> = new Map();

    constructor() {
        this.ingredients.push(Ingredient.of("Egg"));
        this.ingredients.push(Ingredient.of("Cheese"));
        this.ingredients.push(Ingredient.of("Salt"));

        const omelette = Name.named("Omelette");
        const hamAndEggs = Name.named("Ham and Eggs");
        this.recipes.push(omelette);
        this.recipes.push(hamAndEggs);
        this.recipes.push(Name.named("Sandwich"));
        this.recipesByName.set(omelette.toString(), new Recipe(omelette, Name.named("Egg"), Name.named("Cheese")));
        this.recipesByName.set(hamAndEggs.toString(), new Recipe(hamAndEggs, Name.named("Bacon"), Name.named("Egg")));
    }

    public selectIngredients(): Ingredient[] {
        return [...this.ingredients];
    }

    public selectAllRecipes(): Name[] {
        return [...this.recipes];
    }

    public selectRecipeFor(meal: Name): Recipe | undefined {
        return this.recipesByName.get(meal.toString());
    }

    public insertRecipe(recipe: Recipe): void {
        this.recipes.push(recipe.getNameOfMeal());
        this.recipesByName.set(recipe.getNameOfMeal().toString(), recipe);
    }

    public removeRecipe(nameOfMeal: Name): void {
        const index = this.recipes.indexOf(nameOfMeal);
        if (index !== -1) {
            this.recipes.splice(index, 1);
            this.recipesByName.delete(nameOfMeal.toString());
        }
    }
}
