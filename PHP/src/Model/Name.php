<?php

namespace Minimal\Model;

final class Name
{
    private string $name;

    public static function named(string $name): Name
    {
        return new Name($name);
    }

    private function __construct(string $name)
    {
        $this->name = $name;
    }

    public function isNamedLike(Name $other): bool
    {
        return $this->equalsIgnoreCase($other);
    }

    private function equalsIgnoreCase(Name $other): bool
    {
        return strcasecmp($this->name, $other->name) === 0;
    }

    public function __toString()
    {
        return $this->name;
    }
}
