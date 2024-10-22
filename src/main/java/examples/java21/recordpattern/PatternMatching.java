package examples.java21.recordpattern;

import examples.java17.sealedclasses.*;

import java.util.List;

public class PatternMatching {

    // Q: what does this method do?
    // A: This method validates the shape of a given object.
    public static String enhancedValidateShape(Shape shape) {
        return switch(shape) {
            case Circle c when c.getNumberOfSizes() == 0 -> "this is a circle";
            case Circle c when c.getNumberOfSizes() != 0 -> "This is a weird circle";
            case Triangle t when t.getNumberOfSizes() == 3 -> "this is a triangle";
            case Square s when s.getNumberOfSizes() == 4 -> {
                System.out.println(s.getNumberOfSizes());
                yield "this is a square";
            }
            default -> "unknown shape";
        };
    }

    public static void main(String[] args) {
        var shapesList = List.of(new Circle(), new Triangle(), new Square(), new EquilateralTriangle());

        shapesList.stream().map(PatternMatching::enhancedValidateShape).forEach(System.out::println);
    }

}
