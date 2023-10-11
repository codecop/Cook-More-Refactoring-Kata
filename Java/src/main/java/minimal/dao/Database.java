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
    private final List<Name> recipies = new ArrayList<>();
    private final Map<Name, Recipe> recipiesByName = new HashMap<>();

    public Database() {
        ingredients.addAll(Arrays.asList(Ingredient.of("Egg"), Ingredient.of("Cheese"), Ingredient.of("Salt")));

        Name omelett = named("Omelett");
        Name hamAndEggs = named("Ham and Eggs");
        recipies.addAll(Arrays.asList(omelett, hamAndEggs, named("Sandwich")));
        recipiesByName.put(omelett, new Recipe(omelett, named("Egg"), named("Cheese")));
        recipiesByName.put(hamAndEggs, new Recipe(hamAndEggs, named("Bacon"), named("Egg")));
    }

    public List<Ingredient> selectIngredients() {
        return ingredients;
    }

    public List<Name> selectAllRecipies() {
        return recipies;
    }

    public Recipe selectRecipeFor(Name meal) {
        return recipiesByName.get(meal);
    }

    public void insertRecipe(Recipe recipe) {
        recipies.add(recipe.nameOfMeal());
        recipiesByName.put(recipe.nameOfMeal(), recipe);
    }

    public void removeRecipe(Name nameOfMeal) {
        recipies.remove(nameOfMeal);
        recipiesByName.remove(nameOfMeal);
    }

}
