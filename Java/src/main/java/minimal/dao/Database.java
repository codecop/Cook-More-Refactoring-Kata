package minimal.dao;

import static minimal.model.Name.named;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minimal.model.Ingredient;
import minimal.model.Name;
import minimal.model.Recipe;

/**
 * Fake database code to read ingredients.
 */
public class Database {

    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<Name> recipes = new ArrayList<>();
    private final Map<Name, Recipe> recipesByName = new HashMap<>();

    public Database() {
        ingredients.addAll(Arrays.asList(Ingredient.of("Egg"), Ingredient.of("Cheese"), Ingredient.of("Salt")));

        Name omelette = named("Omelette");
        Name hamAndEggs = named("Ham and Eggs");
        recipes.addAll(Arrays.asList(omelette, hamAndEggs, named("Sandwich")));
        recipesByName.put(omelette, new Recipe(omelette, named("Egg"), named("Cheese")));
        recipesByName.put(hamAndEggs, new Recipe(hamAndEggs, named("Bacon"), named("Egg")));
    }

    public List<Ingredient> selectIngredients() {
        return ingredients;
    }

    public List<Name> selectAllRecipes() {
        return recipes;
    }

    public Recipe selectRecipeFor(Name meal) {
        return recipesByName.get(meal);
    }

    public void insertRecipe(Recipe recipe) {
        recipes.add(recipe.nameOfMeal());
        recipesByName.put(recipe.nameOfMeal(), recipe);
    }

    public void removeRecipe(Name nameOfMeal) {
        recipes.remove(nameOfMeal);
        recipesByName.remove(nameOfMeal);
    }

}
