package CampusServiceManagementSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ============================================================================
 * SERVICE REQUEST CLASS
 * ============================================================================
 * Connects a Student with a requested CampusService and tracks the lifecycle
 * state and payment information.
 * ============================================================================
 */
public class ServiceRequest {

    private String requestId;
    private Student student;
    private CampusService service;
    private RequestStatus status;
    private String dateRequested;
    private Payment payment;

    public ServiceRequest(String requestId, Student student, CampusService service) {
        this.requestId = requestId;
        this.student = student;
        this.service = service;
        this.status = RequestStatus.PENDING;
        this.dateRequested = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.payment = null;
    }

    public String getRequestId() {
        return requestId;
    }

    public Student getStudent() {
        return student;
    }

    public CampusService getService() {
        return service;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public Payment getPayment() {
        return payment;
    }

    public boolean isPaid() {
        return payment != null && payment.isSuccessful();
    }

    public double getTotalPrice() {
        return service.calculatePrice();
    }

    /**
     * Updates request status adhering strictly to business rules:
     * - A completed request cannot be cancelled.
     */
    public boolean updateStatus(RequestStatus newStatus) {
        if (this.status == RequestStatus.COMPLETED && newStatus == RequestStatus.CANCELLED) {
            System.out.println("ERROR: Completed requests cannot be cancelled.");
            return false;
        }

        if (this.status == RequestStatus.CANCELLED) {
            System.out.println("ERROR: Cancelled requests cannot be modified.");
            return false;
        }

        this.status = newStatus;
        return true;
    }

    /**
     * Executes payment for this request using polymorphism (accepts any Payment subtype).
     */
    public boolean processPayment(Payment paymentMethod, double amount) {
        // Business Rule: Can only pay if Status is PENDING
        if (this.status == RequestStatus.COMPLETED) {
            System.out.println("ERROR: This request has already been completed.");
            System.out.println("Payment is not allowed.");
            return false;
        }

        if (this.status == RequestStatus.CANCELLED) {
            System.out.println("ERROR: Cannot pay for a cancelled request.");
            return false;
        }

        if (isPaid()) {
            System.out.println("ERROR: This request has already been paid.");
            return false;
        }

        // Business Rule: Amount validation
        if (amount <= 0) {
            System.out.println("ERROR: Payment amount must be greater than zero.");
            return false;
        }

        double requiredAmount = getTotalPrice();
        if (amount > requiredAmount) {
            System.out.println("ERROR: Payment exceeds required amount.");
            System.out.printf("Required: ₱%,.2f | Attempted: ₱%,.2f%n", requiredAmount, amount);
            return false;
        }

        if (amount < requiredAmount) {
            System.out.println("ERROR: Payment is less than required amount.");
            System.out.printf("Required: ₱%,.2f | Attempted: ₱%,.2f%n", requiredAmount, amount);
            return false;
        }

        // Polymorphic call: executes Cash, GCash, or Card payment processing
        boolean success = paymentMethod.processPayment(amount);
        if (success) {
            this.payment = paymentMethod;
            // Update status upon successful payment
            this.status = RequestStatus.PROCESSING;
            return true;
        }

        return false;
    }

    public void displaySummary() {
        System.out.println("Request ID: " + requestId);
        System.out.println("Student: " + student.getStudentId() + " - " + student.getFullName());
        System.out.println("Service: " + service.getServiceName());
        System.out.printf("Price: ₱%,.2f%n", getTotalPrice());
        System.out.println("Status: " + status);
    }
}
