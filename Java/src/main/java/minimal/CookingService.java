package minimal;

import java.util.ArrayList;
import java.util.List;

import minimal.dao.Database;
import minimal.model.Cookbook;
import minimal.model.Ingredient;
import minimal.model.Name;
import minimal.model.NoSuchIngredientException;
import minimal.model.Recipe;

public class CookingService {

    private final Database database = new Database();

    public List<Name> listIngredients() {
        return namesOf(getIngredients());
    }

    private List<Ingredient> getIngredients() {
        return database.selectIngredients();
    }

    private List<Name> namesOf(List<Ingredient> ingredients) {
        List<Name> names = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            names.add(ingredient.name());
        }
        return names;
    }

    public Ingredient takeOut(Name name) {
        Ingredient ingredient = findIngredientWith(name);
        ingredient.ensureOpened();
        return ingredient;
    }

    private Ingredient findIngredientWith(Name name) {
        for (Ingredient ingredient : getIngredients()) {
            if (ingredient.name().isNamedLike(name)) {
                return ingredient;
            }
        }
        throw new NoSuchIngredientException(name);
    }

    private List<Ingredient> takeOut(List<Name> listOfIngredients) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Name name : listOfIngredients) {
            ingredients.add(takeOut(name));
        }
        return ingredients;
    }

    public Cookbook getCookbook() {
        return new Cookbook(listAllRecipes());
    }

    private List<Name> listAllRecipes() {
        return database.selectAllRecipies();
    }

    public Recipe howToCook(Name meal) {
        return findRecipeFor(meal);
    }

    private Recipe findRecipeFor(Name meal) {
        return database.selectRecipeFor(meal);
    }

    public List<Ingredient> takeOutAllFor(Recipe recipe) {
        return takeOut(recipe.listIngredients());
    }

    public void addToMyCookbook(Recipe recipe) {
        database.insertRecipe(recipe);
    }

    public void removeFromCookbook(Name nameOfMeal) {
        database.removeRecipe(nameOfMeal);
    }
}
