package CampusServiceManagementSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * PILLAR 1: ENCAPSULATION
 * ============================================================================
 * Represents a Student in the Campus Service System.
 * 
 * - All attributes are declared 'private' to prevent direct outside tampering.
 * - Balance cannot be modified arbitrarily (e.g., student.balance = -50000).
 * - Safe access is granted via public getters and controlled balance methods
 *   like addBalance() and deductBalance().
 * - Student ID is auto-incremented sequentially in YYMMDD## format.
 * ============================================================================
 */
public class Student {

    // ------------------------------------------------------------------------
    // Auto-Increment Sequential Tracker (Format: YYMMDD##)
    // ------------------------------------------------------------------------
    private static String lastDatePrefix = "";
    private static int idSequence = 1;

    /**
     * Generates an auto-incremented Student ID in YYMMDD## format.
     * Example: 26100801, 26100802
     */
    public static synchronized String generateNextId() {
        String todayPrefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        if (!todayPrefix.equals(lastDatePrefix)) {
            lastDatePrefix = todayPrefix;
            idSequence = 1;
        }
        return String.format("%s%02d", todayPrefix, idSequence++);
    }

    // ------------------------------------------------------------------------
    // ENCAPSULATION: Private member variables
    // ------------------------------------------------------------------------
    private String studentId;
    private String fullName;
    private String course;
    private int yearLevel;
    private double balance;
    private List<ServiceRequest> serviceRequests;

    // Constructor with auto-generated ID (YYMMDD##)
    public Student(String fullName, String course, int yearLevel, double initialBalance) {
        this(generateNextId(), fullName, course, yearLevel, initialBalance);
    }

    // Constructor with explicit ID (if needed)
    public Student(String studentId, String fullName, String course, int yearLevel, double initialBalance) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.course = course;
        setYearLevel(yearLevel);
        this.balance = Math.max(0.0, initialBalance);
        this.serviceRequests = new ArrayList<>();
    }

    // ------------------------------------------------------------------------
    // Getters and Setters with validation
    // ------------------------------------------------------------------------
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if (studentId != null && !studentId.trim().isEmpty()) {
            this.studentId = studentId.trim();
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName != null && !fullName.trim().isEmpty()) {
            this.fullName = fullName.trim();
        }
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(int yearLevel) {
        if (yearLevel >= 1 && yearLevel <= 6) {
            this.yearLevel = yearLevel;
        } else {
            this.yearLevel = 1;
        }
    }

    // ------------------------------------------------------------------------
    // ENCAPSULATION: Controlled balance operations
    // ------------------------------------------------------------------------
    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            System.out.println("  [Validation Error] Added amount must be greater than zero.");
        }
    }

    public boolean deductBalance(double amount) {
        if (amount <= 0) {
            System.out.println("  [Validation Error] Deduction amount must be greater than zero.");
            return false;
        }
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("  [Validation Error] Insufficient student balance.");
            return false;
        }
    }

    // ------------------------------------------------------------------------
    // Service Requests Management
    // ------------------------------------------------------------------------
    public List<ServiceRequest> getServiceRequests() {
        return serviceRequests;
    }

    public void addServiceRequest(ServiceRequest request) {
        this.serviceRequests.add(request);
    }

    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + fullName);
        System.out.println("Course: " + course);
        System.out.println("Year Level: " + yearLevel);
        System.out.printf("Balance: ₱%,.2f%n", balance);
    }
}