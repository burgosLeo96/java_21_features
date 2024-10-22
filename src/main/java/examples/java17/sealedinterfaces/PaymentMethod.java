package examples.java17.sealedinterfaces;

public sealed interface PaymentMethod permits Cash, Card {
    void pay();
}
