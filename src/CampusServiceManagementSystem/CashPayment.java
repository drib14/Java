package CampusServiceManagementSystem;

/**
 * ============================================================================
 * PILLAR 2: INHERITANCE & PILLAR 3: POLYMORPHISM
 * ============================================================================
 * Concrete implementation for Cash payments.
 * ============================================================================
 */
public class CashPayment extends Payment {

    public CashPayment(String transactionId) {
        super(transactionId, "Cash");
    }

    // ------------------------------------------------------------------------
    // [POLYMORPHISM] Overriding processPayment()
    // ------------------------------------------------------------------------
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Cash payment...");
        System.out.printf("Cash payment of ₱%,.2f received.%n", amount);
        System.out.println("Receipt stamped and cash registered.");
        setAmountPaid(amount);
        setSuccessful(true);
        return true;
    }
}
