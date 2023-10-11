import { CookingService } from "./CookingService";
import { Cookbook } from "./model/Cookbook";
import { Meal } from "./model/Meal";
import { Name } from "./model/Name";
import { Pot } from "./model/Pot";
import { Recipe } from "./model/Recipe";
import { WeCanNotCookAnythingException } from "./WeCanNotCookAnythingException";

/**
 * A typical client of the service.
 */
export class Client {

    private readonly cookingService: CookingService;

    constructor(cookingService: CookingService) {
        this.cookingService = cookingService;
    }

    public eatDinnerWithWhatWeHaveAtHome(): void {
        const availableIngredients: Name[] = this.whatDoWeHaveToEat();
        const recipe: Recipe | undefined = this.findRecipeFor(availableIngredients);
        if (recipe) {
            const meal: Meal = this.prepareMeal(recipe);
            meal.eat();
        } else {
            throw new WeCanNotCookAnythingException();
        }
    }

    private whatDoWeHaveToEat(): Name[] {
        return this.cookingService.listIngredients();
    }

    private findRecipeFor(availableIngredients: Name[]): Recipe | undefined {
        const cookbook: Cookbook = this.cookingService.getCookbook();
        for (const nameOfMeal of cookbook.tableOfContents()) {
            const recipe: Recipe | undefined = this.cookingService.howToCook(nameOfMeal);
            if (recipe && recipe.canBePreparedWith(availableIngredients)) {
                return recipe;
            }
        }
        return undefined;
    }

    private prepareMeal(recipe: Recipe): Meal {
        const pot: Pot = new Pot();
        this.mixEverythingTogether(pot, recipe);
        this.alwaysPutExtraSalt(pot);
        return pot.cook();
    }

    private mixEverythingTogether(pot: Pot, recipe: Recipe): void {
        pot.addAll(this.cookingService.takeOutAllFor(recipe));
    }

    private alwaysPutExtraSalt(pot: Pot): void {
        // I like extra Salt to everything
        pot.add(this.cookingService.takeOutIngredient(Name.named("Salt")));
    }

}

if (require.main === module) {
    new Client(new CookingService()).eatDinnerWithWhatWeHaveAtHome();
}
