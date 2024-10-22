package examples.java17.sealedclasses;

// Sealed classes cannot use classes from another package
public sealed class Shape permits Triangle, Circle, Rectangle {
    private final Integer numberOfSizes;

    public Shape(int numberOfSizes) {
        this.numberOfSizes = numberOfSizes;
    }

    public Integer getNumberOfSizes() {
        return this.numberOfSizes;
    }
}
