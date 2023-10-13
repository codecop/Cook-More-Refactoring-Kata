<?php

namespace Minimal\Model;

use Minimal\Model\Ingredient;

class Pot
{
    private array $ingredients = [];

    public function add(Ingredient $ingredient): bool
    {
        return array_push($this->ingredients, $ingredient);
    }

    public function addAll(array $collection): bool
    {
        return array_push($this->ingredients, ...$collection);
    }

    public function cook(): Meal
    {
        echo "cooking " . implode(", ", $this->ingredients) . " - stir stir stir\n";
        return new Meal();
    }
}
