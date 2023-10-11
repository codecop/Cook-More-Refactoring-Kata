package minimal.model;

public final class Name {

    private final String name;

    public static Name named(String name) {
        return new Name(name);
    }

    private Name(String name) {
        this.name = name;
    }

    public boolean isNamedLike(Name other) {
        return this.equalsIgnoreCase(other);
    }

    private boolean equalsIgnoreCase(Name other) {
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Name)) {
            return false;
        }
        Name that = (Name) other;
        return this.name.equals(that.name);
    }

    @Override
    public String toString() {
        return name;
    }

}
