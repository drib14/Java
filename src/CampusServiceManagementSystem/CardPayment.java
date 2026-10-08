package CampusServiceManagementSystem;

/**
 * ============================================================================
 * PILLAR 2: INHERITANCE & PILLAR 3: POLYMORPHISM
 * ============================================================================
 * Concrete implementation for Debit/Credit Card payments.
 * ============================================================================
 */
public class CardPayment extends Payment {

    public CardPayment(String transactionId) {
        super(transactionId, "Card");
    }

    // ------------------------------------------------------------------------
    // [POLYMORPHISM] Overriding processPayment()
    // ------------------------------------------------------------------------
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Card payment...\n");
        System.out.println("Connecting to Payment Gateway...");
        System.out.println("Authorizing card transaction...");
        System.out.println("Card payment approved!");
        setAmountPaid(amount);
        setSuccessful(true);
        return true;
    }
}
