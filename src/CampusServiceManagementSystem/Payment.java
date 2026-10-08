package CampusServiceManagementSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ============================================================================
 * PILLAR 4: ABSTRACTION & PILLAR 2: INHERITANCE (Payment Base Class)
 * ============================================================================
 * Abstract base class for handling student payments.
 * 
 * - Cannot be directly instantiated with 'new Payment()'.
 * - Forces every payment method (Cash, GCash, Card) to define its own
 *   concrete payment processing logic through processPayment().
 * ============================================================================
 */
public abstract class Payment {

    private String transactionId;
    private String paymentMethod;
    private double amountPaid;
    private String paymentDate;
    private boolean isSuccessful;

    public Payment(String transactionId, String paymentMethod) {
        this.transactionId = transactionId;
        this.paymentMethod = paymentMethod;
        this.paymentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.isSuccessful = false;
        this.amountPaid = 0.0;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    protected void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    protected void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    // ------------------------------------------------------------------------
    // [ABSTRACTION] Abstract Method for payment processing
    // ------------------------------------------------------------------------
    public abstract boolean processPayment(double amount);
}
