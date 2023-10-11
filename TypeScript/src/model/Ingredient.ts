import { Name } from "./Name";

/**
 * An ingredient to prepare meals, e.g. Milk or Bacon. An ingredient can be fresh or already opened.
 */
export class Ingredient {

    private readonly name: Name;
    private opened: boolean;

    public static of(name: string | Name): Ingredient {
        if (typeof (name) === "string") {
            return new Ingredient(Name.named(name));
        } else {
            return new Ingredient(name);
        }
    }

    private constructor(name: Name) {
        this.name = name;
        this.opened = false;
    }

    public getName(): Name {
        return this.name;
    }

    public ensureOpened(): void {
        if (!this.isOpen()) {
            this.open();
        }
    }

    public isOpen(): boolean {
        return this.opened;
    }

    private open(): void {
        this.opened = true;
    }

    public equals(other: any): boolean {
        if (other == null || !(other instanceof Ingredient)) {
            return false;
        }
        const that: Ingredient = other;
        return this.name.equals(that.name);
    }

    public toString(): string {
        return (this.isOpen() ? "Open " : "Fresh ") + this.name.toString();
    }
}
