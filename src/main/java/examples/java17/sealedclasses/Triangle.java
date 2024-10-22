package examples.java17.sealedclasses;

public sealed class Triangle extends Shape permits RectangleTriangle, EquilateralTriangle {
    public Triangle() {
        super(3);
    }
}
