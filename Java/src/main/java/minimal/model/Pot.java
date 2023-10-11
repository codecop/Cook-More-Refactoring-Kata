package minimal.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pot {

    private final List<Ingredient> ingredients = new ArrayList<>();

    public boolean add(Ingredient e) {
        return ingredients.add(e);
    }

    public boolean addAll(Collection<Ingredient> c) {
        return ingredients.addAll(c);
    }

    public Meal cook() {
        System.out.println("cooking " + ingredients + " - stir stir stir");
        return new Meal();
    }

}
