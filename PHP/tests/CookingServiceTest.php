<?php

namespace Minimal;

use PHPUnit\Framework\TestCase;
use Minimal\Model\Name;
use Minimal\Model\Ingredient;
use Minimal\Model\Recipe;
use Minimal\Model\Cookbook;
use Minimal\Dao\Database;

class CookingServiceTest extends TestCase
{
    private CookingService $cookingService;

    private static Name $EGG;
    private static Name $CHEESE;
    private static Name $SALT;
    private static Name $OMELETTE;

    public function setUp(): void
    {
        $this->cookingService = new CookingService();

        self::$EGG = Name::named("Egg");
        self::$CHEESE = Name::named("Cheese");
        self::$SALT = Name::named("Salt");
        self::$OMELETTE = Name::named("Omelette");
    }

    public function testWhatDoWeHaveToEat()
    {
        $ingredients = $this->cookingService->listIngredients();

        $expectedIngredients = [self::$EGG, self::$CHEESE, self::$SALT];
        $this->assertEquals($expectedIngredients, $ingredients);
    }

    public function testTakeOut()
    {
        $cheese = $this->cookingService->takeOut(self::$CHEESE);

        $this->assertInstanceOf(Ingredient::class, $cheese);
        $this->assertTrue($cheese->isOpen());
    }

    public function testTakeOutCaseInsensitive()
    {
        $egg = $this->cookingService->takeOut(Name::named("egg"));

        $this->assertInstanceOf(Ingredient::class, $egg);
    }

    public function testTakeOutMissing()
    {
        $this->expectException(NoSuchIngredientException::class);
        $this->cookingService->takeOut(Name::named("Bacon"));
    }

    public function testHowToCook()
    {
        $omelette = $this->cookingService->howToCook(self::$OMELETTE);

        $this->assertInstanceOf(Recipe::class, $omelette);
        $expectedIngredients = [self::$EGG, self::$CHEESE];
        $this->assertEquals($expectedIngredients, $omelette->listIngredients());
    }

    public function testTakeOutAllFor()
    {
        $omelette = $this->cookingService->howToCook(self::$OMELETTE);
        $ingredients = $this->cookingService->takeOutAllFor($omelette);

        $expectedIngredients = [Ingredient::of(self::$EGG), Ingredient::of(self::$CHEESE)];
        $this->assertEquals($expectedIngredients, $ingredients);
    }

    public function testAddToMyCookbook()
    {
        $somethingTasty = Name::named("test recipe");
        $this->cookingService->addToMyCookbook(new Recipe($somethingTasty, self::$EGG));

        $recipe = $this->cookingService->howToCook($somethingTasty);

        $this->assertInstanceOf(Recipe::class, $recipe);
        $this->assertEquals([self::$EGG], $recipe->listIngredients());
    }

    public function testRemoveFromCookbook()
    {
        $this->cookingService->removeFromCookbook(self::$OMELETTE);

        $cookbook = $this->cookingService->getCookbook();
        $this->assertFalse(in_array(self::$OMELETTE, $cookbook->tableOfContents()));
    }

    public function testRemoveFromCookbookTwice()
    {
        $this->cookingService->removeFromCookbook(self::$OMELETTE);

        $this->expectException(NoSuchIngredientException::class);
        $this->cookingService->takeOut(self::$OMELETTE);
    }
}
