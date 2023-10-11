import { Name } from "./Name";

export class Cookbook {

    private readonly meals: Name[];

    constructor(meals: Name[]) {
        this.meals = meals;
    }

    public tableOfContents(): Name[] {
        return [...this.meals];
    }
}
