import { CookingService } from "../src/CookingService";
import { Name } from "../src/model/Name";
import { Ingredient } from "../src/model/Ingredient";
import { Recipe } from "../src/model/Recipe";
import { NoSuchIngredientException } from "../src/model/NoSuchIngredientException";

const EGG = Name.named("Egg");
const CHEESE = Name.named("Cheese");
const SALT = Name.named("Salt");
const OMELETTE = Name.named("Omelette");

describe("CookingService", () => {
    let cookingService: CookingService;

    beforeEach(() => {
        cookingService = new CookingService();
    });

    it("should list available ingredients", () => {
        const ingredients: Name[] = cookingService.listIngredients();

        expect(ingredients).toEqual([EGG, CHEESE, SALT]);
    });

    it("should take out an ingredient", () => {
        const cheese: Ingredient = cookingService.takeOutIngredient(CHEESE);

        expect(cheese).toBeDefined();
        expect(cheese.isOpen()).toBeTruthy();
    });

    it("should take out an ingredient case-insensitive", () => {
        const egg: Ingredient = cookingService.takeOutIngredient(EGG);

        expect(egg).toBeDefined();
    });

    it("should throw NoSuchIngredientException when taking out a missing ingredient", () => {
        expect(() => cookingService.takeOutIngredient(Name.named("Bacon"))).toThrow(NoSuchIngredientException);
    });

    it("should provide recipe for a dish", () => {
        const omelette: Recipe | undefined = cookingService.howToCook(OMELETTE);

        expect(omelette).toBeDefined();
        expect(omelette!.listIngredients()).toEqual([EGG, CHEESE]);
    });

    it("should take out all ingredients for a recipe", () => {
        const omelette: Recipe | undefined = cookingService.howToCook(OMELETTE);
        const ingredients: Ingredient[] = cookingService.takeOutAllFor(omelette!);

        const egg = Ingredient.of(EGG);
        egg.ensureOpened();
        const cheese = Ingredient.of(CHEESE);
        cheese.ensureOpened();
        expect(ingredients).toEqual([egg, cheese]);
    });

    it("should add a recipe to the cookbook", () => {
        const somethingTasty: Name = Name.named("test recipe");
        cookingService.addToMyCookbook(new Recipe(somethingTasty, EGG));

        const recipe: Recipe | undefined = cookingService.howToCook(somethingTasty);

        expect(recipe).toBeDefined();
        expect(recipe!.listIngredients()).toEqual([EGG]);
    });

    it("should remove a recipe from the cookbook", () => {
        cookingService.removeFromCookbook(OMELETTE);

        expect(cookingService.getCookbook().tableOfContents()).not.toContain(OMELETTE);
    });

    it("should throw NoSuchIngredientException when removing a missing recipe from the cookbook", () => {
        cookingService.removeFromCookbook(OMELETTE);

        expect(() => cookingService.takeOutIngredient(OMELETTE)).toThrow(NoSuchIngredientException);
    });
});
