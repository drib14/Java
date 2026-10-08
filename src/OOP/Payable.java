package OOP;

/**
 * ============================================================================
 * PILLAR 4: ABSTRACTION (Interface Contract)
 * ============================================================================
 * An Interface represents pure abstraction:
 * - It defines WHAT an object must be able to do, but NOT HOW it does it.
 * - All methods in an interface are by default 'public' and 'abstract'.
 * - Any class that implements 'Payable' MUST provide concrete code for these.
 * ============================================================================
 */
public interface Payable {

    // Abstract method: calculates the salary/compensation
    double calculatePay();

    // Abstract method: prints the breakdown of payment
    void printPayslip();
}
