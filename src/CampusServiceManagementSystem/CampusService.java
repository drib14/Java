package CampusServiceManagementSystem;

/**
 * ============================================================================
 * PILLAR 4: ABSTRACTION & PILLAR 2: INHERITANCE (Base Class)
 * ============================================================================
 * An abstract blueprint for all campus services.
 * 
 * - Cannot be instantiated directly with 'new CampusService()'.
 * - Declares the abstract method 'calculatePrice()' which enforces that all
 *   derived service types MUST provide their own pricing calculation algorithm.
 * ============================================================================
 */
public abstract class CampusService {

    // Encapsulated common service properties
    private String serviceId;
    private String serviceName;
    private double basePrice;

    public CampusService(String serviceId, String serviceName, double basePrice) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.basePrice = Math.max(0.0, basePrice);
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = Math.max(0.0, basePrice);
    }

    // ------------------------------------------------------------------------
    // [ABSTRACTION] Abstract Methods: Must be implemented by child classes
    // ------------------------------------------------------------------------
    public abstract double calculatePrice();

    public abstract void printServiceSummary();
}
