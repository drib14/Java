package OOP;

/**
 * ============================================================================
 * PILLAR 3: POLYMORPHISM (and PILLAR 2: INHERITANCE)
 * ============================================================================
 * 
 * 1. INHERITANCE:
 *    - 'Developer' extends 'Employee' ("Developer IS-A Employee IS-A Human").
 *    - Reuses all Human and Employee properties and methods automatically.
 * 
 * 2. RUNTIME POLYMORPHISM (Method Overriding - Dynamic Binding):
 *    - Uses '@Override' to give custom implementations to abstract parent methods:
 *      * work()
 *      * performDailyRoutine()
 *      * calculatePay() (Adds a 10% tech bonus to base salary)
 * 
 * 3. COMPILE-TIME POLYMORPHISM (Method Overloading - Static Binding):
 *    - Multiple methods with the SAME name ('work'), but DIFFERENT parameter lists:
 *      * work()             -> No parameters (default job)
 *      * work(String task)  -> 1 String parameter (specific task)
 *      * work(int hours)    -> 1 int parameter (working duration)
 * ============================================================================
 */
public class Developer extends Employee {

    // Encapsulated Developer-specific field
    private String primaryLanguage;

    public Developer(String fullName, int age, String nationality, 
                     String employeeId, double baseSalary, String primaryLanguage) {
        // Pass common fields to parent (Employee) constructor
        super(fullName, age, nationality, employeeId, "Software Engineering", baseSalary);
        this.primaryLanguage = (primaryLanguage != null && !primaryLanguage.trim().isEmpty()) 
                                ? primaryLanguage.trim() : "Java";
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    // ------------------------------------------------------------------------
    // [RUNTIME POLYMORPHISM] Method Overriding
    // ------------------------------------------------------------------------
    @Override
    public void work() {
        System.out.println("  [Developer Duty] " + getFullName() + " is coding and debugging backend services in " 
                + primaryLanguage + ".");
    }

    @Override
    public void performDailyRoutine() {
        System.out.println("  [Developer Routine] " + getFullName() + ": Coffee -> Stand-up meeting -> Coding in " 
                + primaryLanguage + " -> Code Review.");
    }

    @Override
    public double calculatePay() {
        // Developers receive their base salary plus a 10% code innovation bonus
        double bonus = getBaseSalary() * 0.10;
        return getBaseSalary() + bonus;
    }

    // ------------------------------------------------------------------------
    // [COMPILE-TIME POLYMORPHISM] Method Overloading
    // Same method name 'work', different signatures!
    // ------------------------------------------------------------------------
    
    // Overload 1: Takes a specific task name
    public void work(String task) {
        System.out.println("  [Overloaded work(task)] " + getFullName() + " is building: \"" + task + "\" using " 
                + primaryLanguage + ".");
    }

    // Overload 2: Takes hours worked
    public void work(int hours) {
        System.out.println("  [Overloaded work(hours)] " + getFullName() + " clocked in " + hours 
                + " hours of focused coding today.");
    }
}
