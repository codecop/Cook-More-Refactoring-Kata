<?php

namespace Minimal\Model;

class Cookbook
{
    private array $meals;

    public function __construct(array $meals)
    {
        $this->meals = $meals;
    }

    public function tableOfContents(): array
    {
        return $this->meals;
    }
}
