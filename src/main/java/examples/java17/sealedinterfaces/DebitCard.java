package examples.java17.sealedinterfaces;

public final class DebitCard implements Card {

    @Override
    public void pay() {
        System.out.println("Credit card payment method called. Generating CCV");
        this.generateCCV();
    }

    @Override
    public void generateCCV() {
        System.out.println("Debit Card CCV generation started.");
    }
}
