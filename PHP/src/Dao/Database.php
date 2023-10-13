<?php

namespace Minimal\Dao;

use Minimal\Model\Ingredient;
use Minimal\Model\Name;
use Minimal\Model\Recipe;

/**
 * Fake database code to read ingredients.
 */
class Database
{
    private array $ingredients = [];
    private array $recipes = [];
    private array $recipesByName = [];

    public function __construct()
    {
        $this->ingredients = [
            Ingredient::of("Egg"),
            Ingredient::of("Cheese"),
            Ingredient::of("Salt"),
        ];

        $omelette = Name::named("Omelette");
        $hamAndEggs = Name::named("Ham and Eggs");
        $this->recipes = [$omelette, $hamAndEggs, Name::named("Sandwich")];

        $this->recipesByName[$omelette] = new Recipe($omelette, Name::named("Egg"), Name::named("Cheese"));
        $this->recipesByName[$hamAndEggs] = new Recipe($hamAndEggs, Name::named("Bacon"), Name::named("Egg"));
    }

    public function selectIngredients(): array
    {
        return $this->ingredients;
    }

    public function selectAllRecipes(): array
    {
        return $this->recipes;
    }

    public function selectRecipeFor(Name $meal): Recipe
    {
        return $this->recipesByName[$meal];
    }

    public function insertRecipe(Recipe $recipe)
    {
        $this->recipes[] = $recipe->nameOfMeal();
        $this->recipesByName[$recipe->nameOfMeal()] = $recipe;
    }

    public function removeRecipe(Name $nameOfMeal)
    {
        $index = array_search($nameOfMeal, $this->recipes);
        if ($index !== false) {
            unset($this->recipes[$index]);
        }
        unset($this->recipesByName[$nameOfMeal]);
    }
}
