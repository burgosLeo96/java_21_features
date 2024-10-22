package examples.java17.sealedinterfaces;

public sealed interface Card extends PaymentMethod permits CreditCard, DebitCard {
    void generateCCV();
}
