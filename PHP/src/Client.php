<?php

namespace Minimal;

use Minimal\Model\Cookbook;
use Minimal\Model\Meal;
use Minimal\Model\Name;
use Minimal\Model\Pot;
use Minimal\Model\Recipe;

/**
 * A typical client of the service.
 */
class Client
{
    private CookingService $cookingService;

    public function __construct(CookingService $cookingService)
    {
        $this->cookingService = $cookingService;
    }

    public function eatDinnerWithWhatWeHaveAtHome()
    {
        $availableIngredients = $this->whatDoWeHaveToEat();
        $recipe = $this->findRecipeFor($availableIngredients);
        $meal = $this->prepareMeal($recipe);
        $meal->eat();
    }

    private function whatDoWeHaveToEat(): array
    {
        return $this->cookingService->listIngredients();
    }

    private function findRecipeFor(array $availableIngredients): Recipe
    {
        $cookbook = $this->cookingService->getCookbook();
        foreach ($cookbook->tableOfContents() as $nameOfMeal) {

            $recipe = $this->cookingService->howToCook($nameOfMeal);
            if ($recipe->canBePreparedWith($availableIngredients)) {
                return $recipe;
            }
            
        }
        throw new WeCanNotCookAnythingException();
    }

    private function prepareMeal(Recipe $recipe): Meal
    {
        $pot = new Pot();
        $this->mixEverythingTogether($pot, $recipe);
        $this->alwaysPutExtraSalt($pot);
        return $pot->cook();
    }

    private function mixEverythingTogether(Pot $pot, Recipe $recipe)
    {
        $pot->addAll($this->cookingService->takeOutAllFor($recipe));
    }

    private function alwaysPutExtraSalt(Pot $pot)
    {
        // I like extra Salt in everything
        $pot->add($this->cookingService->takeOut(Name::named("Salt")));
    }

    public static function main()
    {
        $client = new Client(new CookingService());
        $client->eatDinnerWithWhatWeHaveAtHome();
    }

}
