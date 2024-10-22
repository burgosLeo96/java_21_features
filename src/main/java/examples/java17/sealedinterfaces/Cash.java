package examples.java17.sealedinterfaces;

public final class Cash implements PaymentMethod {

    @Override
    public void pay() {
        System.out.println("Cash payment method called");
    }
}
