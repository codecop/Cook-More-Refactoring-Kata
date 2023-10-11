package minimal.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Recipe for a meal. A recipe contains the list of the names of the needed ingredients and a
 * description how to prepare them.
 */
public class Recipe {

    private final Name nameOfMeal;
    private final List<Name> ingredients;

    public Recipe(Name nameOfMeal, Name... ingredients) {
        this.nameOfMeal = nameOfMeal;
        this.ingredients = Arrays.asList(ingredients);
    }

    public Name nameOfMeal() {
        return nameOfMeal;
    }

    public List<Name> listIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public boolean canBePreparedWith(List<Name> availableIngredients) {
        return availableIngredients.containsAll(ingredients);
    }

    @Override
    public String toString() {
        return "Recipe to prepare " + nameOfMeal.toString();
    }

}
