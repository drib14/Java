package CampusServiceManagementSystem;

/**
 * ============================================================================
 * REQUEST STATUS ENUM
 * ============================================================================
 * Represents the lifecycle stages of a service request in the system:
 * - PENDING    : Initial state upon creation; awaiting payment.
 * - PROCESSING : Payment received; campus department is preparing the request.
 * - COMPLETED  : Request fulfilled and claimed by student.
 * - CANCELLED  : Request cancelled (cannot cancel if already COMPLETED).
 * ============================================================================
 */
public enum RequestStatus {
    PENDING,
    PROCESSING,
    COMPLETED,
    CANCELLED
}
