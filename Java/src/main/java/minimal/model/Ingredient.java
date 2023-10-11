package minimal.model;

import static minimal.model.Name.named;

/**
 * An ingredient to prepare meals, e.g. Milk or Bacon. An ingredient can be fresh or already opened.
 */
public final class Ingredient {

    private final Name name;
    private boolean opened;

    public static Ingredient of(String name) {
        return of(named(name));
    }

    public static Ingredient of(Name name) {
        return new Ingredient(name);
    }

    private Ingredient(Name name) {
        this.name = name;
    }

    public Name name() {
        return name;
    }

    public void ensureOpened() {
        if (!isOpen()) {
            open();
        }
    }

    public boolean isOpen() {
        return opened;
    }

    private void open() {
        opened = true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Ingredient)) {
            return false;
        }
        Ingredient that = (Ingredient) other;
        return this.name.equals(that.name);
    }

    @Override
    public String toString() {
        return (isOpen() ? "Open " : "Fresh ") + name;
    }

}
