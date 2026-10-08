package OOP;

/**
 * ============================================================================
 * PILLAR 2: INHERITANCE (and implements PILLAR 4: ABSTRACTION)
 * ============================================================================
 * 
 * 1. INHERITANCE:
 *    - 'Employee' extends 'Human' ("Employee IS-A Human").
 *    - Automatically inherits: fullName, age, nationality, introduce(), sleep().
 *    - Does NOT need to rewrite those fields or methods (Code Reusability).
 *    - Uses 'super(...)' to pass initial data to the Human parent constructor.
 *    - Adds specialized fields: employeeId, department, baseSalary.
 * 
 * 2. ABSTRACTION:
 *    - Implements the 'Payable' interface contract.
 *    - Declares 'public abstract void work();' because all employees work,
 *      but each job works in its own specific way.
 * ============================================================================
 */
public abstract class Employee extends Human implements Payable {

    // ------------------------------------------------------------------------
    // [ENCAPSULATION] Private employee-specific attributes
    // ------------------------------------------------------------------------
    private String employeeId;
    private String department;
    private double baseSalary;

    // Constructor: Uses 'super(...)' to initialize Human parent properties
    public Employee(String fullName, int age, String nationality, 
                    String employeeId, String department, double baseSalary) {
        // Pass parent variables to Human constructor
        super(fullName, age, nationality);
        
        this.employeeId = employeeId;
        this.department = department;
        setBaseSalary(baseSalary);
    }

    // ------------------------------------------------------------------------
    // [ENCAPSULATION] Getters and Setters with validation
    // ------------------------------------------------------------------------
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0) {
            System.out.println("  [Validation Warning] Salary cannot be negative! Defaulting to $0.00.");
            this.baseSalary = 0.0;
        } else {
            this.baseSalary = baseSalary;
        }
    }

    // ------------------------------------------------------------------------
    // [POLYMORPHISM] Method Overriding: Customizes the parent's introduce()
    // ------------------------------------------------------------------------
    @Override
    public void introduce() {
        // Reuse parent introduction logic first
        super.introduce();
        System.out.println("  -> [Employee Info] ID: " + employeeId 
                + " | Department: " + department 
                + " | Base Salary: $" + String.format("%.2f", baseSalary));
    }

    // ------------------------------------------------------------------------
    // [ABSTRACTION] Abstract method: Every concrete child class MUST define this
    // ------------------------------------------------------------------------
    public abstract void work();

    // ------------------------------------------------------------------------
    // [ABSTRACTION] Implementing Payable interface methods
    // ------------------------------------------------------------------------
    @Override
    public double calculatePay() {
        // Base pay calculation
        return baseSalary;
    }

    @Override
    public void printPayslip() {
        System.out.println("----------------------------------------------");
        System.out.println("  PAYSLIP: " + getFullName() + " (" + employeeId + ")");
        System.out.println("  Department: " + department);
        System.out.printf("  Total Compensation: $%.2f%n", calculatePay());
        System.out.println("----------------------------------------------");
    }
}
