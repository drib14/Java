package CampusServiceManagementSystem;

/**
 * ============================================================================
 * PILLAR 2: INHERITANCE & PILLAR 3: POLYMORPHISM
 * ============================================================================
 * Represents an ID Replacement service.
 * 
 * - Inherits from CampusService.
 * - Adds specific field: reason ("Lost", "Damaged", "Expired").
 * - Overrides calculatePrice(): Returns flat base price (₱150.00).
 * ============================================================================
 */
public class IDReplacement extends CampusService {

    private String reason;

    public IDReplacement(String serviceId, String reason, double basePrice) {
        super(serviceId, "ID Replacement", basePrice);
        this.reason = (reason != null && !reason.trim().isEmpty()) ? reason.trim() : "Lost";
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // ------------------------------------------------------------------------
    // [POLYMORPHISM] Overriding calculatePrice()
    // ID replacement has a flat rate equal to the base price
    // ------------------------------------------------------------------------
    @Override
    public double calculatePrice() {
        return getBasePrice();
    }

    @Override
    public void printServiceSummary() {
        System.out.println("Service: ID Replacement");
        System.out.println("Reason: " + reason);
        System.out.printf("Total Price: ₱%,.2f%n", calculatePrice());
    }
}
