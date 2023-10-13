<?php

namespace Minimal\Model;

class NoSuchIngredientException extends \RuntimeException
{
    public function __construct(Name $ingredient)
    {
        parent::__construct($ingredient->__toString());
    }
}
