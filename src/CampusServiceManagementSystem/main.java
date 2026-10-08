package CampusServiceManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ============================================================================
 * CAMPUS SERVICE MANAGEMENT SYSTEM - MAIN CONSOLE APPLICATION
 * ============================================================================
 * Implements the full interactive console interface and orchestrates:
 * 1. Encapsulation : Student & Service data protection
 * 2. Inheritance   : CampusService & Payment class hierarchies
 * 3. Polymorphism  : Polymorphic price calculations & payment processing
 * 4. Abstraction   : Abstract CampusService and Payment base classes
 * ============================================================================
 */
public class main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();
    private static final List<ServiceRequest> serviceRequests = new ArrayList<>();
    private static int requestCounter = 1;
    private static int transactionCounter = 1;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMainMenu();
            System.out.print("Enter choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    registerStudent();
                    break;
                case "2":
                    viewStudents();
                    break;
                case "3":
                    createServiceRequest();
                    break;
                case "4":
                    viewServiceRequests();
                    break;
                case "5":
                    payServiceRequest();
                    break;
                case "6":
                    updateRequestStatus();
                    break;
                case "7":
                    viewRequestDetails();
                    break;
                case "8":
                    running = false;
                    System.out.println("\nThank you for using the Campus Service Management System. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 8.\n");
                    break;
            }
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("       CAMPUS SERVICE SYSTEM");
        System.out.println("========================================");
        System.out.println();
        System.out.println("1. Register Student");
        System.out.println("2. View Students");
        System.out.println("3. Create Service Request");
        System.out.println("4. View Service Requests");
        System.out.println("5. Pay Service Request");
        System.out.println("6. Update Request Status");
        System.out.println("7. View Request Details");
        System.out.println("8. Exit");
        System.out.println();
    }

    // ========================================================================
    // 1. REGISTER STUDENT (Demonstrates Encapsulation & Auto-Increment ID)
    // ========================================================================
    private static void registerStudent() {
        System.out.println("\n========================================");
        System.out.println("          REGISTER STUDENT");
        System.out.println("========================================");
        System.out.println();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("ERROR: Name cannot be empty.");
            return;
        }

        System.out.print("Enter Course: ");
        String course = scanner.nextLine().trim();

        System.out.print("Enter Year Level: ");
        int yearLevel;
        try {
            yearLevel = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Year Level must be a valid number.");
            return;
        }

        System.out.print("Enter Initial Balance: ");
        double balance;
        try {
            balance = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Initial Balance must be a valid numeric value.");
            return;
        }

        // Auto-incremented Student object creation (format: YYMMDD##)
        Student student = new Student(name, course, yearLevel, balance);
        students.add(student);

        System.out.println("\nStudent registered successfully!\n");
        student.displayStudentInfo();
    }

    // ========================================================================
    // 2. VIEW STUDENTS
    // ========================================================================
    private static void viewStudents() {
        System.out.println("\n========================================");
        System.out.println("           REGISTERED STUDENTS");
        System.out.println("========================================");

        if (students.isEmpty()) {
            System.out.println("No students registered in the system yet.");
            return;
        }

        for (Student s : students) {
            System.out.println("----------------------------------------");
            s.displayStudentInfo();
        }
        System.out.println("----------------------------------------");
    }

    // ========================================================================
    // 3. CREATE SERVICE REQUEST (Demonstrates Abstraction & Polymorphism)
    // ========================================================================
    private static void createServiceRequest() {
        System.out.println("\n========================================");
        System.out.println("       CREATE SERVICE REQUEST");
        System.out.println("========================================");
        System.out.println();

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("\nERROR: Student not found.");
            return;
        }

        System.out.println("\nAvailable Services:\n");
        System.out.println("1. Document Request");
        System.out.println("2. ID Replacement");
        System.out.println("3. Laboratory Reservation");
        System.out.print("\nEnter choice: ");
        String choice = scanner.nextLine().trim();

        CampusService service = null;
        String reqId = String.format("REQ-%03d", requestCounter);

        switch (choice) {
            case "1":
                service = handleDocumentRequestCreation(reqId);
                break;
            case "2":
                service = handleIDReplacementCreation(reqId);
                break;
            case "3":
                service = handleLabReservationCreation(reqId);
                break;
            default:
                System.out.println("ERROR: Invalid service choice.");
                return;
        }

        if (service == null) {
            return;
        }

        // Polymorphic reference: CampusService can be DocumentRequest, IDReplacement, or LabReservation
        ServiceRequest request = new ServiceRequest(reqId, student, service);
        serviceRequests.add(request);
        student.addServiceRequest(request);
        requestCounter++;

        System.out.println("\nRequest created successfully!\n");
        System.out.println("Request ID: " + request.getRequestId());
        System.out.println("Student: " + student.getFullName());

        if (service instanceof DocumentRequest) {
            DocumentRequest doc = (DocumentRequest) service;
            System.out.println("Service: " + doc.getDocumentType());
            System.out.println("Pages: " + doc.getNumberOfPages());
            System.out.println();
            System.out.printf("Base Price: ₱%,.2f%n", doc.getBasePrice());
            System.out.printf("Additional Pages: ₱%,.2f%n", doc.getAdditionalPagesFee());
            System.out.println();
            System.out.printf("Total Price: ₱%,.2f%n", doc.calculatePrice());
        } else if (service instanceof LaboratoryReservation) {
            LaboratoryReservation lab = (LaboratoryReservation) service;
            System.out.println("Laboratory Name: " + lab.getLaboratoryName());
            System.out.println("Number of Hours: " + lab.getHours());
            System.out.println();
            System.out.printf("Hourly Rate: ₱%,.2f%n", lab.getHourlyRate());
            System.out.printf("Total Price: ₱%,.2f%n", lab.calculatePrice());
        } else if (service instanceof IDReplacement) {
            IDReplacement id = (IDReplacement) service;
            System.out.println("Service: ID Replacement");
            System.out.println("Reason: " + id.getReason());
            System.out.println();
            System.out.printf("Total Price: ₱%,.2f%n", id.calculatePrice());
        }

        System.out.println();
        System.out.println("Status: " + request.getStatus());
    }

    private static CampusService handleDocumentRequestCreation(String reqId) {
        System.out.println("\n========================================");
        System.out.println("        DOCUMENT REQUEST");
        System.out.println("========================================");
        System.out.println("\nEnter document type:");
        System.out.println("1. Certificate of Enrollment");
        System.out.println("2. Good Moral Certificate");
        System.out.println("3. Transcript Request");
        System.out.print("\nEnter choice: ");
        String docChoice = scanner.nextLine().trim();

        String docType;
        switch (docChoice) {
            case "1":
                docType = "Certificate of Enrollment";
                break;
            case "2":
                docType = "Good Moral Certificate";
                break;
            case "3":
                docType = "Transcript Request";
                break;
            default:
                System.out.println("ERROR: Invalid document type.");
                return null;
        }

        System.out.print("\nEnter number of pages: ");
        int pages;
        try {
            pages = Integer.parseInt(scanner.nextLine().trim());
            if (pages <= 0) {
                System.out.println("ERROR: Number of pages must be at least 1.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid page number.");
            return null;
        }

        // Document base price is ₱150.00
        return new DocumentRequest(reqId + "-DOC", docType, pages, 150.0);
    }

    private static CampusService handleIDReplacementCreation(String reqId) {
        System.out.println("\n========================================");
        System.out.println("         ID REPLACEMENT");
        System.out.println("========================================");
        System.out.println("\nEnter reason for replacement:");
        System.out.println("1. Lost");
        System.out.println("2. Damaged");
        System.out.println("3. Expired");
        System.out.print("\nEnter choice: ");
        String reasonChoice = scanner.nextLine().trim();

        String reason;
        switch (reasonChoice) {
            case "1":
                reason = "Lost";
                break;
            case "2":
                reason = "Damaged";
                break;
            case "3":
                reason = "Expired";
                break;
            default:
                reason = "Lost";
                break;
        }

        // Fixed price ₱150.00
        return new IDReplacement(reqId + "-ID", reason, 150.0);
    }

    private static CampusService handleLabReservationCreation(String reqId) {
        System.out.println("\n========================================");
        System.out.println("      LABORATORY RESERVATION");
        System.out.println("========================================");
        System.out.println();

        System.out.print("Laboratory Name: ");
        String labName = scanner.nextLine().trim();
        if (labName.isEmpty()) {
            labName = "Computer Laboratory 1";
        }

        System.out.print("Number of Hours: ");
        int hours;
        try {
            hours = Integer.parseInt(scanner.nextLine().trim());
            if (hours <= 0) {
                System.out.println("ERROR: Hours must be at least 1.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid number of hours.");
            return null;
        }

        double hourlyRate = 100.0;
        return new LaboratoryReservation(reqId + "-LAB", labName, hours, hourlyRate);
    }

    // ========================================================================
    // 4. VIEW SERVICE REQUESTS (Polymorphism in action)
    // ========================================================================
    private static void viewServiceRequests() {
        System.out.println("\n========================================");
        System.out.println("       SERVICE REQUESTS");
        System.out.println("========================================");
        System.out.println();

        if (serviceRequests.isEmpty()) {
            System.out.println("No service requests recorded yet.");
            return;
        }

        for (ServiceRequest req : serviceRequests) {
            System.out.println("Request ID: " + req.getRequestId());
            System.out.println("Student: " + req.getStudent().getStudentId() + " - " + req.getStudent().getFullName());
            
            // Polymorphism: Calling service name
            if (req.getService() instanceof DocumentRequest) {
                DocumentRequest doc = (DocumentRequest) req.getService();
                System.out.println("Service: " + doc.getDocumentType());
            } else if (req.getService() instanceof LaboratoryReservation) {
                System.out.println("Service: Laboratory Reservation");
            } else {
                System.out.println("Service: " + req.getService().getServiceName());
            }

            // Polymorphism: calculatePrice() executes the subclass implementation!
            System.out.printf("Price: ₱%,.2f%n", req.getTotalPrice());
            System.out.println("Status: " + req.getStatus());
            System.out.println("----------------------------------------\n");
        }
    }

    // ========================================================================
    // 5. PAY SERVICE REQUEST (Demonstrates Polymorphism + Abstraction)
    // ========================================================================
    private static void payServiceRequest() {
        System.out.println("\n========================================");
        System.out.println("          PAYMENT");
        System.out.println("========================================");
        System.out.println();

        System.out.print("Enter Request ID: ");
        String reqId = scanner.nextLine().trim();

        ServiceRequest request = findRequestById(reqId);
        if (request == null) {
            System.out.println("\nERROR: Request not found.");
            return;
        }

        // Business Rule: Check completion or existing payment
        if (request.getStatus() == RequestStatus.COMPLETED) {
            System.out.println("\nERROR: This request has already been completed.");
            System.out.println("Payment is not allowed.");
            return;
        }

        if (request.isPaid()) {
            System.out.println("\nERROR: This request has already been paid.");
            return;
        }

        if (request.getStatus() == RequestStatus.CANCELLED) {
            System.out.println("\nERROR: Cannot pay for a cancelled request.");
            return;
        }

        System.out.println("\nRequest Information");
        System.out.println("----------------------------------------");
        System.out.println("Student: " + request.getStudent().getFullName());

        if (request.getService() instanceof DocumentRequest) {
            DocumentRequest doc = (DocumentRequest) request.getService();
            System.out.println("Service: " + doc.getDocumentType());
        } else {
            System.out.println("Service: " + request.getService().getServiceName());
        }

        System.out.printf("Amount Due: ₱%,.2f%n", request.getTotalPrice());
        System.out.println("Status: " + request.getStatus());
        System.out.println("----------------------------------------\n");

        System.out.println("Payment Methods:\n");
        System.out.println("1. Cash");
        System.out.println("2. GCash");
        System.out.println("3. Card");
        System.out.print("\nChoose payment method: ");
        String paymentChoice = scanner.nextLine().trim();

        String txnId = String.format("TXN-%03d", transactionCounter);
        Payment paymentMethod;

        switch (paymentChoice) {
            case "1":
                paymentMethod = new CashPayment(txnId);
                break;
            case "2":
                paymentMethod = new GCashPayment(txnId);
                break;
            case "3":
                paymentMethod = new CardPayment(txnId);
                break;
            default:
                System.out.println("ERROR: Invalid payment method.");
                return;
        }

        System.out.print("\nEnter amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid amount format.");
            return;
        }

        System.out.println();
        // Polymorphic payment execution
        boolean paid = request.processPayment(paymentMethod, amount);

        if (paid) {
            transactionCounter++;
            System.out.println("\n========================================");
            System.out.println("          PAYMENT RECEIPT");
            System.out.println("========================================");
            System.out.println();
            System.out.println("Transaction ID: " + paymentMethod.getTransactionId());
            System.out.println("Request ID: " + request.getRequestId());
            System.out.println();
            System.out.println("Student: " + request.getStudent().getFullName());

            if (request.getService() instanceof DocumentRequest) {
                DocumentRequest doc = (DocumentRequest) request.getService();
                System.out.println("Service: " + doc.getDocumentType());
            } else {
                System.out.println("Service: " + request.getService().getServiceName());
            }

            System.out.println();
            System.out.printf("Amount Paid: ₱%,.2f%n", paymentMethod.getAmountPaid());
            System.out.println("Payment Method: " + paymentMethod.getPaymentMethod());
            System.out.println();
            System.out.println("Payment Status: PAID");
            System.out.println();
            System.out.println("Request Status: " + request.getStatus());
            System.out.println("========================================");
        }
    }

    // ========================================================================
    // 6. UPDATE REQUEST STATUS (Adheres to business rules)
    // ========================================================================
    private static void updateRequestStatus() {
        System.out.println("\n========================================");
        System.out.println("       UPDATE REQUEST STATUS");
        System.out.println("========================================");
        System.out.println();

        System.out.print("Enter Request ID: ");
        String reqId = scanner.nextLine().trim();

        ServiceRequest request = findRequestById(reqId);
        if (request == null) {
            System.out.println("\nERROR: Request not found.");
            return;
        }

        RequestStatus currentStatus = request.getStatus();
        System.out.println("\nCurrent Status: " + currentStatus);
        System.out.println("\nAvailable Status:\n");
        System.out.println("1. PROCESSING");
        System.out.println("2. COMPLETED");
        System.out.println("3. CANCELLED");
        System.out.print("\nEnter choice: ");
        String choice = scanner.nextLine().trim();

        RequestStatus newStatus;
        switch (choice) {
            case "1":
                newStatus = RequestStatus.PROCESSING;
                break;
            case "2":
                newStatus = RequestStatus.COMPLETED;
                break;
            case "3":
                newStatus = RequestStatus.CANCELLED;
                break;
            default:
                System.out.println("ERROR: Invalid status selection.");
                return;
        }

        boolean updated = request.updateStatus(newStatus);
        if (updated) {
            System.out.println("\nRequest status updated successfully!\n");
            System.out.println(request.getRequestId());
            System.out.println(currentStatus + " → " + newStatus);
        }
    }

    // ========================================================================
    // 7. VIEW REQUEST DETAILS
    // ========================================================================
    private static void viewRequestDetails() {
        System.out.println("\n========================================");
        System.out.println("        REQUEST DETAILS");
        System.out.println("========================================");
        System.out.println();

        System.out.print("Enter Request ID: ");
        String reqId = scanner.nextLine().trim();

        ServiceRequest request = findRequestById(reqId);
        if (request == null) {
            System.out.println("\nERROR: Request not found.");
            return;
        }

        CampusService service = request.getService();
        Student student = request.getStudent();

        System.out.printf("%-17s: %s%n", "Request ID", request.getRequestId());
        System.out.printf("%-17s: %s%n", "Student ID", student.getStudentId());
        System.out.printf("%-17s: %s%n", "Student Name", student.getFullName());
        System.out.println();

        if (service instanceof DocumentRequest) {
            DocumentRequest doc = (DocumentRequest) service;
            System.out.printf("%-17s: %s%n", "Service", doc.getDocumentType());
            System.out.printf("%-17s: %d%n", "Document Pages", doc.getNumberOfPages());
            System.out.println();
            System.out.printf("%-17s: ₱%,.2f%n", "Base Price", doc.getBasePrice());
            System.out.printf("%-17s: ₱%,.2f%n", "Additional Fees", doc.getAdditionalPagesFee());
            System.out.printf("%-17s: ₱%,.2f%n", "Total Price", doc.calculatePrice());
        } else if (service instanceof LaboratoryReservation) {
            LaboratoryReservation lab = (LaboratoryReservation) service;
            System.out.printf("%-17s: %s%n", "Service", "Laboratory Reservation");
            System.out.printf("%-17s: %s%n", "Laboratory Name", lab.getLaboratoryName());
            System.out.printf("%-17s: %d hours%n", "Hours", lab.getHours());
            System.out.println();
            System.out.printf("%-17s: ₱%,.2f%n", "Hourly Rate", lab.getHourlyRate());
            System.out.printf("%-17s: ₱%,.2f%n", "Total Price", lab.calculatePrice());
        } else if (service instanceof IDReplacement) {
            IDReplacement id = (IDReplacement) service;
            System.out.printf("%-17s: %s%n", "Service", "ID Replacement");
            System.out.printf("%-17s: %s%n", "Reason", id.getReason());
            System.out.println();
            System.out.printf("%-17s: ₱%,.2f%n", "Total Price", id.calculatePrice());
        }

        System.out.println();
        if (request.isPaid()) {
            System.out.printf("%-17s: %s%n", "Payment Method", request.getPayment().getPaymentMethod());
            System.out.printf("%-17s: %s%n", "Payment Status", "PAID");
        } else {
            System.out.printf("%-17s: %s%n", "Payment Method", "None");
            System.out.printf("%-17s: %s%n", "Payment Status", "UNPAID");
        }

        System.out.println();
        System.out.printf("%-17s: %s%n", "Request Status", request.getStatus());
        System.out.println("========================================");
    }

    // ========================================================================
    // HELPER LOOKUP METHODS
    // ========================================================================
    private static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    private static ServiceRequest findRequestById(String id) {
        for (ServiceRequest r : serviceRequests) {
            if (r.getRequestId().equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }
}
