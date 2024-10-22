package examples.java17.sealedinterfaces;

public final class CreditCard implements Card {

    @Override
    public void pay() {
        System.out.println("CreditCard payment method called. Generating CCV");
        this.generateCCV();
    }

    @Override
    public void generateCCV() {
        System.out.println("Credit Card CCV generation started");
    }
}
