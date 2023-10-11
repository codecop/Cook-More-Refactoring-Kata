package minimal;

import java.util.List;

import minimal.model.Cookbook;
import minimal.model.Meal;
import minimal.model.Name;
import minimal.model.Pot;
import minimal.model.Recipe;

/**
 * A typical client of the service.
 */
public class Client {

    private final CookingService cookingService;

    public Client(CookingService cookingService) {
        this.cookingService = cookingService;
    }

    public void eatDinnerWithWhatWeHaveAtHome() {
        List<Name> availableIngredients = whatDoWeHaveToEat();
        Recipe recipe = findRecipeFor(availableIngredients);
        Meal meal = prepareMeal(recipe);
        meal.eat();
    }

    private List<Name> whatDoWeHaveToEat() {
        return cookingService.listIngredients(); 
    }

    private Recipe findRecipeFor(List<Name> availableIngredients) {
        Cookbook cookbook = cookingService.getCookbook(); 
        for (Name nameOfMeal : cookbook.tableOfContents()) {

            Recipe recipe = cookingService.howToCook(nameOfMeal); 
            if (recipe.canBePreparedWith(availableIngredients)) {
                return recipe;
            }

        }
        throw new WeCanNotCookAnythingException();
    }

    private Meal prepareMeal(Recipe recipe) {
        Pot pot = new Pot();
        mixEverythingTogether(pot, recipe);
        alwaysPutExtraSalt(pot);
        return pot.cook();
    }

    private void mixEverythingTogether(Pot pot, Recipe recipe) {
        pot.addAll(cookingService.takeOutAllFor(recipe)); 
    }

    private void alwaysPutExtraSalt(Pot pot) {
        // I like extra Salt to everything
        pot.add(cookingService.takeOut(Name.named("Salt"))); 
    }

    public static void main(String... args) {
        new Client(new CookingService()).eatDinnerWithWhatWeHaveAtHome();
    }
}
