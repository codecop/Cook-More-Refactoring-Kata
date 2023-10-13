<?php

namespace Minimal\Model;

/**
 * An ingredient to prepare meals, e.g. Milk or Bacon. An ingredient can be fresh or already opened.
 */
class Ingredient
{
    private Name $name;
    private bool $opened;

    public static function of($name): Ingredient
    {
        if (is_string($name)) {
            return new Ingredient(Name::named($name));
        } else {
            return new Ingredient($name);
        }
    }

    private function __construct(Name $name)
    {
        $this->name = $name;
        $this->opened = false;
    }

    public function name(): Name
    {
        return $this->name;
    }

    public function ensureOpened()
    {
        if (!$this->isOpen()) {
            $this->open();
        }
    }

    public function isOpen(): bool
    {
        return $this->opened;
    }

    private function open()
    {
        $this->opened = true;
    }

    public function __toString()
    {
        return ($this->isOpen() ? "Open " : "Fresh ") . $this->name;
    }
}
