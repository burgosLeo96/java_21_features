package examples.java17;

import examples.java17.sealedclasses.*;
import examples.java17.sealedinterfaces.Cash;
import examples.java17.sealedinterfaces.CreditCard;
import examples.java17.sealedinterfaces.DebitCard;
import examples.java17.sealedinterfaces.PaymentMethod;

import java.util.List;

public class SwitchPatternMatching {

    public static String validateShape(Shape shape) {
        return switch(shape) {
            case Circle c -> "this is a circle";
            case Triangle t -> "this is a triangle";
            case Square square -> {
                System.out.println(square.getNumberOfSizes());
                yield "this is a square";
            }
            default -> "unknown shape";
        };
    }

    public static void main(String[] args) {
        var shapesList = List.of(new Circle(), new Triangle(), new Square(), new EquilateralTriangle());

        var paymentMethods = List.of(new Cash(), new CreditCard(), new DebitCard());

        paymentMethods.forEach(PaymentMethod::pay);

        System.out.println("\n\n");

        shapesList.stream().map(SwitchPatternMatching::validateShape).forEach(System.out::println);
    }

}
