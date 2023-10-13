<?php

namespace Minimal\Model;

/**
 * Recipe for a meal. A recipe contains the list of the names of the needed ingredients and a
 * description how to prepare them.
 */
class Recipe
{
    private Name $nameOfMeal;
    private array $ingredients;

    public function __construct(Name $nameOfMeal, Name ...$ingredients)
    {
        $this->nameOfMeal = $nameOfMeal;
        $this->ingredients = $ingredients;
    }

    public function nameOfMeal(): Name
    {
        return $this->nameOfMeal;
    }

    public function listIngredients(): array
    {
        return $this->ingredients;
    }

    public function canBePreparedWith(array $availableIngredients): bool
    {
        return count(array_diff($this->ingredients, $availableIngredients)) === 0;
    }

    public function __toString()
    {
        return "Recipe to prepare " . $this->nameOfMeal->__toString();
    }
}
