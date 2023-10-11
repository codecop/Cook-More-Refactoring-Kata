package minimal.model;

public class NoSuchIngredientException extends RuntimeException {

    public NoSuchIngredientException(Name ingredient) {
        super(ingredient.toString());
    }

}
