package CampusServiceManagementSystem;

/**
 * ============================================================================
 * PILLAR 2: INHERITANCE & PILLAR 3: POLYMORPHISM
 * ============================================================================
 * Concrete implementation for GCash digital wallet payments.
 * ============================================================================
 */
public class GCashPayment extends Payment {

    public GCashPayment(String transactionId) {
        super(transactionId, "GCash");
    }

    // ------------------------------------------------------------------------
    // [POLYMORPHISM] Overriding processPayment()
    // ------------------------------------------------------------------------
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing GCash payment...\n");
        System.out.println("Connecting to GCash...");
        System.out.println("Verifying payment...");
        System.out.println("Payment successful!");
        setAmountPaid(amount);
        setSuccessful(true);
        return true;
    }
}
