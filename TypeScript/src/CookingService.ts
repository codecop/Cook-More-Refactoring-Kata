import { Database } from "./dao/Database";
import { Cookbook } from "./model/Cookbook";
import { Ingredient } from "./model/Ingredient";
import { Name } from "./model/Name";
import { NoSuchIngredientException } from "./model/NoSuchIngredientException";
import { Recipe } from "./model/Recipe";

export class CookingService {

    private readonly database: Database = new Database();

    public listIngredients(): Name[] {
        return this.namesOf(this.getIngredients());
    }

    private getIngredients(): Ingredient[] {
        return this.database.selectIngredients();
    }

    private namesOf(ingredients: Ingredient[]): Name[] {
        return ingredients.map((ingredient) => ingredient.getName());
    }

    public takeOutIngredient(name: Name): Ingredient {
        const ingredient = this.findIngredientWith(name);
        ingredient.ensureOpened();
        return ingredient;
    }

    private findIngredientWith(name: Name): Ingredient {
        const ingredients = this.getIngredients();
        const foundIngredient = ingredients.find((ingredient) => ingredient.getName().isNamedLike(name));
        if (foundIngredient) {
            return foundIngredient;
        }
        throw new NoSuchIngredientException(name);
    }

    private takeOutIngredients(listOfIngredients: Name[]): Ingredient[] {
        return listOfIngredients.map((name) => this.takeOutIngredient(name));
    }

    public getCookbook(): Cookbook {
        return new Cookbook(this.listAllRecipes());
    }

    private listAllRecipes(): Name[] {
        return this.database.selectAllRecipes();
    }

    public howToCook(meal: Name): Recipe | undefined {
        return this.findRecipeFor(meal);
    }

    private findRecipeFor(meal: Name): Recipe | undefined {
        return this.database.selectRecipeFor(meal);
    }

    public takeOutAllFor(recipe: Recipe): Ingredient[] {
        return this.takeOutIngredients(recipe.listIngredients());
    }

    public addToMyCookbook(recipe: Recipe): void {
        this.database.insertRecipe(recipe);
    }

    public removeFromCookbook(nameOfMeal: Name): void {
        this.database.removeRecipe(nameOfMeal);
    }
}
