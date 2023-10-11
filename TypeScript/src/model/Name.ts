export class Name {

    private readonly name: string;

    public static named(name: string): Name {
        return new Name(name);
    }

    private constructor(name: string) {
        this.name = name;
    }

    public isNamedLike(other: Name): boolean {
        return this.equalsIgnoreCase(other);
    }

    private equalsIgnoreCase(other: Name): boolean {
        return this.name.toLowerCase() === other.name.toLowerCase();
    }

    public equals(other: any): boolean {
        if (other == null || !(other instanceof Name)) {
            return false;
        }
        const that: Name = other;
        return this.name === that.name;
    }

    public toString(): string {
        return this.name;
    }
}
