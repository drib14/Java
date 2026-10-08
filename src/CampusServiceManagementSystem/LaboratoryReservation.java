package CampusServiceManagementSystem;

/**
 * ============================================================================
 * PILLAR 2: INHERITANCE & PILLAR 3: POLYMORPHISM
 * ============================================================================
 * Represents a Laboratory Reservation service.
 * 
 * - Inherits from CampusService.
 * - Adds specific fields: laboratoryName, hours, hourlyRate.
 * - Overrides calculatePrice(): hours * hourlyRate (e.g., 3 * ₱100 = ₱300).
 * ============================================================================
 */
public class LaboratoryReservation extends CampusService {

    private String laboratoryName;
    private int hours;
    private double hourlyRate;

    public LaboratoryReservation(String serviceId, String laboratoryName, int hours, double hourlyRate) {
        super(serviceId, "Laboratory Reservation (" + laboratoryName + ")", 0.0);
        this.laboratoryName = laboratoryName;
        this.hours = Math.max(1, hours);
        this.hourlyRate = Math.max(0.0, hourlyRate);
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = Math.max(1, hours);
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = Math.max(0.0, hourlyRate);
    }

    // ------------------------------------------------------------------------
    // [POLYMORPHISM] Overriding calculatePrice()
    // Formula: hours * hourlyRate
    // ------------------------------------------------------------------------
    @Override
    public double calculatePrice() {
        return hours * hourlyRate;
    }

    @Override
    public void printServiceSummary() {
        System.out.println("Laboratory Name: " + laboratoryName);
        System.out.println("Number of Hours: " + hours);
        System.out.printf("Hourly Rate: ₱%,.2f%n", hourlyRate);
        System.out.printf("Total Price: ₱%,.2f%n", calculatePrice());
    }
}
