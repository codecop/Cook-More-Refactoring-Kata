package minimal;

import static minimal.model.Name.named;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import minimal.model.Ingredient;
import minimal.model.Name;
import minimal.model.NoSuchIngredientException;
import minimal.model.Recipe;

import org.junit.Test;

public class CookingServiceTest {

    private static final Name EGG = named("Egg");
    private static final Name CHEESE = named("Cheese");
    private static final Name SALT = named("Salt");
    private static final Name OMELETTE = named("Omelette");

    private CookingService cookingService = new CookingService();

    @Test
    public void testWhatDoWeHaveToEat() {
        List<Name> ingredients = cookingService.listIngredients();

        assertEquals(Arrays.asList(EGG, CHEESE, SALT), ingredients);
    }

    @Test
    public void testTakeOut() {
        Ingredient cheese = cookingService.takeOut(CHEESE);

        assertNotNull(cheese);
        assertTrue("opened", cheese.isOpen());
    }

    @Test
    public void testTakeOutCaseInsensitive() {
        Ingredient egg = cookingService.takeOut(named("egg"));

        assertNotNull(egg);
    }

    @Test(expected = NoSuchIngredientException.class)
    public void testTakeOutMissing() {
        cookingService.takeOut(named("Bacon"));
    }

    @Test
    public void testHowToCook() {
        Recipe omelette = cookingService.howToCook(OMELETTE);

        assertNotNull(omelette);
        assertEquals(Arrays.asList(EGG, CHEESE), omelette.listIngredients());
    }

    @Test
    public void testTakeOutAllFor() {
        Recipe omelette = cookingService.howToCook(OMELETTE);
        List<Ingredient> ingredients = cookingService.takeOutAllFor(omelette);

        assertEquals(Arrays.asList(Ingredient.of(EGG), Ingredient.of(CHEESE)), ingredients);
    }

    @Test
    public void testAddToMyCookbook() {
        Name somethingTasty = named("test recipe");
        cookingService.addToMyCookbook(new Recipe(somethingTasty, named("Egg")));

        Recipe recipe = cookingService.howToCook(somethingTasty);

        assertNotNull(recipe);
        assertEquals(Arrays.asList(EGG), recipe.listIngredients());
    }

    @Test
    public void testremoveFromCookbook() {
        cookingService.removeFromCookbook(OMELETTE);

        assertFalse("no Omelet", cookingService.getCookbook().tableOfContents().contains(OMELETTE));
    }

    @Test(expected = NoSuchIngredientException.class)
    public void testremoveFromCookbook2() {
        cookingService.removeFromCookbook(OMELETTE);
        cookingService.takeOut(OMELETTE);
    }
}
