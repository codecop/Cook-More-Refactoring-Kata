<?php

namespace Minimal;

use Minimal\Dao\Database;
use Minimal\Model\Cookbook;
use Minimal\Model\Ingredient;
use Minimal\Model\Name;
use Minimal\Model\Recipe;
use Minimal\Model\WeCanNotCookAnythingException;

class CookingService
{
    private Database $database;

    public function __construct()
    {
        $this->database = new Database();
    }

    public function listIngredients(): array
    {
        return $this->namesOf($this->getIngredients());
    }

    private function getIngredients(): array
    {
        return $this->database->selectIngredients();
    }

    private function namesOf(array $ingredients): array
    {
        $names = [];
        foreach ($ingredients as $ingredient) {
            $names[] = $ingredient->name();
        }
        return $names;
    }

    public function takeOut(Name $name): Ingredient
    {
        $ingredient = $this->findIngredientWith($name);
        $ingredient->ensureOpened();
        return $ingredient;
    }

    private function findIngredientWith(Name $name): Ingredient
    {
        $ingredients = $this->getIngredients();
        foreach ($ingredients as $ingredient) {
            if ($ingredient->name()->isNamedLike($name)) {
                return $ingredient;
            }
        }
        throw new NoSuchIngredientException($name);
    }

    private function takeOutAll(array $listOfIngredients): array
    {
        $ingredients = [];
        foreach ($listOfIngredients as $name) {
            $ingredients[] = $this->takeOut($name);
        }
        return $ingredients;
    }

    public function getCookbook(): Cookbook
    {
        return new Cookbook($this->listAllRecipes());
    }

    private function listAllRecipes(): array
    {
        return $this->database->selectAllRecipes();
    }

    public function howToCook(Name $meal): Recipe
    {
        return $this->findRecipeFor($meal);
    }

    private function findRecipeFor(Name $meal): Recipe
    {
        return $this->database->selectRecipeFor($meal);
    }

    public function takeOutAllFor(Recipe $recipe): array
    {
        return $this->takeOutAll($recipe->listIngredients());
    }

    public function addToMyCookbook(Recipe $recipe)
    {
        $this->database->insertRecipe($recipe);
    }

    public function removeFromCookbook(Name $nameOfMeal)
    {
        $this->database->removeRecipe($nameOfMeal);
    }
}
