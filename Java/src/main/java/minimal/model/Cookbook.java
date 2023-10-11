package minimal.model;

import java.util.Collections;
import java.util.List;

public class Cookbook {

    private final List<Name> meals;

    public Cookbook(List<Name> meals) {
        this.meals = meals;
    }

    public List<Name> tableOfContents() {
        return Collections.unmodifiableList(meals);
    }

}
